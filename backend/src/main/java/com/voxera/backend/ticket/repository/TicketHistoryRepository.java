package com.voxera.backend.ticket.repository;

import com.voxera.backend.ticket.entity.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, UUID> {

    List<TicketHistory> findByTicket_TicketIdOrderByCreatedAtAsc(UUID ticketId);
}
