package com.voxera.backend.ticket.service;

import com.voxera.backend.security.CurrentUserService;
import com.voxera.backend.ticket.dto.TicketCommentResponse;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.entity.TicketComment;
import com.voxera.backend.ticket.repository.TicketCommentRepository;
import com.voxera.backend.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketCommentService {

    private final TicketCommentRepository ticketCommentRepository;
    private final TicketService ticketService;
    private final CurrentUserService currentUserService;

    public TicketCommentService(
            TicketCommentRepository ticketCommentRepository,
            TicketService ticketService,
            CurrentUserService currentUserService) {
        this.ticketCommentRepository = ticketCommentRepository;
        this.ticketService = ticketService;
        this.currentUserService = currentUserService;
    }

    @Transactional
    public TicketCommentResponse addComment(UUID ticketId, String content) {
        // Reuses the existing resource-level authorization check.
        Ticket ticket = ticketService.getTicket(ticketId);
        User author = currentUserService.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();

        TicketComment comment = new TicketComment(
                UUID.randomUUID(),
                ticket,
                author,
                content.trim(),
                now
        );

        return TicketCommentResponse.from(
                ticketCommentRepository.save(comment)
        );
    }

    @Transactional(readOnly = true)
    public List<TicketCommentResponse> getComments(UUID ticketId) {
        // Do not query comments until access to the parent ticket is verified.
        ticketService.getTicket(ticketId);

        return ticketCommentRepository
                .findByTicket_TicketIdOrderByCreatedAtAsc(ticketId)
                .stream()
                .map(TicketCommentResponse::from)
                .toList();
    }
}
