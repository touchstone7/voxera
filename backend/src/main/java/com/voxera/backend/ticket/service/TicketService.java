package com.voxera.backend.ticket.service;

import com.voxera.backend.exception.ResourceNotFoundException;
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

    public TicketService(
            TicketRepository ticketRepository,
            UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Ticket createTicket(
            String title,
            String description,
            TicketPriority priority,
            TicketCategory category,
            UUID createdBy) {

        User user = userRepository.findById(createdBy)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + createdBy));

        UUID ticketId = UUID.randomUUID();

        Ticket ticket = new Ticket(
                ticketId,
                generateTicketNumber(),
                title,
                description,
                priority,
                category,
                user,
                LocalDateTime.now()
        );

        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(UUID ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket not found: " + ticketId));
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsForUser(UUID userId) {
        return ticketRepository.findByCreatedBy_UserId(userId);
    }

    @Transactional
    public Ticket startProgress(UUID ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.startProgress();
        return ticket;
    }

    @Transactional
    public Ticket markPending(UUID ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.markPending();
        return ticket;
    }

    @Transactional
    public Ticket resolveTicket(UUID ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.resolve();
        return ticket;
    }

    @Transactional
    public Ticket closeTicket(UUID ticketId) {
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
