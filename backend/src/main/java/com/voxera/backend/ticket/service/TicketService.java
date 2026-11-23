
package com.voxera.backend.ticket.service;

import com.voxera.backend.admin.service.WorkspaceSettingsService;
import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.security.CurrentUserService;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.entity.TicketCategory;
import com.voxera.backend.ticket.enums.TicketHistoryAction;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.repository.TicketCategoryRepository;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCategoryRepository ticketCategoryRepository;
    private final CurrentUserService currentUserService;
    private final TicketAuthorizationService ticketAuthorizationService;
    private final WorkspaceSettingsService workspaceSettingsService;
    private final TicketHistoryService ticketHistoryService;

    public TicketService(
            TicketRepository ticketRepository,
            TicketCategoryRepository ticketCategoryRepository,
            TicketHistoryService ticketHistoryService,
            UserRepository userRepository,
            CurrentUserService currentUserService,
            TicketAuthorizationService ticketAuthorizationService,
            WorkspaceSettingsService workspaceSettingsService) {

        this.ticketRepository = ticketRepository;
        this.ticketCategoryRepository = ticketCategoryRepository;
        this.ticketHistoryService = ticketHistoryService;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.ticketAuthorizationService = ticketAuthorizationService;
        this.workspaceSettingsService = workspaceSettingsService;
    }

    @Transactional
    public Ticket createTicket(
            String title,
            String description,
            TicketPriority priority,
            UUID categoryId) {

        User currentUser = currentUserService.getCurrentUser();

        TicketCategory category = ticketCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ticket category not found: " + categoryId));

        if (!category.isActive()) {
            throw new IllegalStateException(
                    "Inactive ticket categories cannot be used for new tickets");
        }

        TicketPriority effectivePriority = priority != null
                ? priority
                : workspaceSettingsService.getDefaultPriority();

        UUID ticketId = UUID.randomUUID();

        Ticket ticket = new Ticket(
                ticketId,
                generateTicketNumber(),
                title,
                description,
                effectivePriority,
                category,
                currentUser,
                LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);

        ticketHistoryService.record(
                savedTicket,
                currentUser,
                TicketHistoryAction.TICKET_CREATED,
                null,
                savedTicket.getStatus().name());

        return savedTicket;
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(UUID ticketId) {
        User actor = currentUserService.getCurrentUser();
        return getTicket(ticketId, actor);
    }

    private Ticket getTicket(UUID ticketId, User actor) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ticket not found: " + ticketId));

        ticketAuthorizationService.requireResourceAccess(actor, ticket);

        return ticket;
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTickets(UUID createdBy) {

        User actor = currentUserService.getCurrentUser();

        if (createdBy != null) {
            ticketAuthorizationService.requireTicketListAccess(
                    actor,
                    createdBy);

            return ticketRepository.findByCreatedBy_UserId(createdBy);
        }

        if (actor.getRole() == UserRole.EMPLOYEE) {
            return ticketRepository.findByCreatedBy_UserId(
                    actor.getUserId());
        }

        return ticketRepository.findAll();
    }

    @Transactional
    public Ticket startProgress(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        if (workspaceSettingsService.getSettings().isRequireAssignment()
                && ticket.getAssignedTo() == null) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ticket must be assigned to an agent before it can be started");
        }

        String oldStatus = ticket.getStatus().name();

        ticket.startProgress();

        String newStatus = ticket.getStatus().name();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.STATUS_CHANGED,
                oldStatus,
                newStatus);

        return ticket;
    }

    @Transactional
    public Ticket markPending(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        String oldStatus = ticket.getStatus().name();

        ticket.markPending();

        String newStatus = ticket.getStatus().name();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.STATUS_CHANGED,
                oldStatus,
                newStatus);

        return ticket;
    }

    @Transactional
    public Ticket resolveTicket(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        String oldStatus = ticket.getStatus().name();

        ticket.resolve();

        String newStatus = ticket.getStatus().name();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.STATUS_CHANGED,
                oldStatus,
                newStatus);

        return ticket;
    }

    @Transactional
    public Ticket closeTicket(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        String oldStatus = ticket.getStatus().name();

        ticket.close();

        String newStatus = ticket.getStatus().name();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.STATUS_CHANGED,
                oldStatus,
                newStatus);

        return ticket;
    }

    @Transactional
    public Ticket reopenTicket(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        if (!workspaceSettingsService.getSettings().isAllowReopening()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ticket reopening is disabled by workspace settings");
        }

        String oldStatus = ticket.getStatus().name();

        ticket.reopen();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.TICKET_REOPENED,
                oldStatus,
                ticket.getStatus().name());

        return ticket;
    }

    @Transactional
    public Ticket cancelTicket(UUID ticketId) {

        User actor = currentUserService.getCurrentUser();

        Ticket ticket = getTicket(ticketId, actor);

        String oldStatus = ticket.getStatus().name();

        ticket.cancel();

        String newStatus = ticket.getStatus().name();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.STATUS_CHANGED,
                oldStatus,
                newStatus);

        return ticket;
    }

    @Transactional
    public Ticket assignTicket(UUID ticketId, UUID userId) {

        User actor = currentUserService.getCurrentUser();

        ticketAuthorizationService.requireAgentOrAdmin(actor);

        Ticket ticket = getTicket(ticketId, actor);

        String oldAssignee = ticket.getAssignedTo() != null
                ? ticket.getAssignedTo().getUserId().toString()
                : null;

        User assignee = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found: " + userId));

        ticket.assignTo(assignee);

        String newAssignee = assignee.getUserId().toString();

        ticketHistoryService.record(
                ticket,
                actor,
                TicketHistoryAction.ASSIGNED,
                oldAssignee,
                newAssignee);

        return ticketRepository.save(ticket);
    }

    private String generateTicketNumber() {
        return workspaceSettingsService.getTicketPrefix() +
                "-" +
                LocalDateTime.now().getYear() +
                "-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
