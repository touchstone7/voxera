package com.voxera.backend.ticket.repository;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    List<Ticket> findByCreatedBy_UserId(UUID userId);

    List<Ticket> findByStatus(TicketStatus status);
}
