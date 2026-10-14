package com.voxera.backend.ticket.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.security.CurrentUserService;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final TicketAuthorizationService ticketAuthorizationService;

    public TicketService(
            TicketRepository ticketRepository,
            UserRepository userRepository,
            CurrentUserService currentUserService,
            TicketAuthorizationService ticketAuthorizationService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.ticketAuthorizationService = ticketAuthorizationService;
    }

    @Transactional
    public Ticket createTicket(
            String title,
            String description,
            TicketPriority priority,
            TicketCategory category) {

        User currentUser = currentUserService.getCurrentUser();

        UUID ticketId = UUID.randomUUID();

        Ticket ticket = new Ticket(
                ticketId,
                generateTicketNumber(),
                title,
                description,
                priority,
                category,
                currentUser,
                LocalDateTime.now()
        );

        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket not found: " + ticketId));

        User actor = currentUserService.getCurrentUser();
        ticketAuthorizationService.requireResourceAccess(actor, ticket);

        return ticket;
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsForUser(UUID userId) {
        User actor = currentUserService.getCurrentUser();
        ticketAuthorizationService.requireTicketListAccess(actor, userId);
        return ticketRepository.findByCreatedBy_UserId(userId);
    }

    @Transactional
    public Ticket startProgress(UUID ticketId) {
        ticketAuthorizationService.requireAgentOrAdmin(
                currentUserService.getCurrentUser());
        Ticket ticket = getTicket(ticketId);
        ticket.startProgress();
        return ticket;
    }

    @Transactional
    public Ticket markPending(UUID ticketId) {
        ticketAuthorizationService.requireAgentOrAdmin(
                currentUserService.getCurrentUser());
        Ticket ticket = getTicket(ticketId);
        ticket.markPending();
        return ticket;
    }

    @Transactional
    public Ticket resolveTicket(UUID ticketId) {
        ticketAuthorizationService.requireAgentOrAdmin(
                currentUserService.getCurrentUser());
        Ticket ticket = getTicket(ticketId);
        ticket.resolve();
        return ticket;
    }

    @Transactional
    public Ticket closeTicket(UUID ticketId) {
        ticketAuthorizationService.requireAgentOrAdmin(
                currentUserService.getCurrentUser());
        Ticket ticket = getTicket(ticketId);
        ticket.close();
        return ticket;
    }

    @Transactional
    public Ticket cancelTicket(UUID ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.cancel();
        return ticket;
    }

    @Transactional
    public Ticket assignTicket(UUID ticketId, UUID userId) {
        ticketAuthorizationService.requireAgentOrAdmin(
                currentUserService.getCurrentUser());
        Ticket ticket = getTicket(ticketId);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + userId));

        ticket.assignTo(user);

        return ticketRepository.save(ticket);
    }

    private String generateTicketNumber() {
        return "INC-" +
                LocalDateTime.now().getYear() +
                "-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
