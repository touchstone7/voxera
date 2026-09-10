package com.voxera.backend.ticket.repository;

import com.voxera.backend.ticket.entity.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketCommentRepository extends JpaRepository<TicketComment, UUID> {

    List<TicketComment> findByTicket_TicketIdOrderByCreatedAtAsc(UUID ticketId);
}
