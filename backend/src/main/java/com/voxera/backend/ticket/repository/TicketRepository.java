
package com.voxera.backend.ticket.repository;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    @Override
    @EntityGraph(attributePaths = {
            "category",
            "createdBy",
            "assignedTo",
            "assignedTeam"
    })
    Optional<Ticket> findById(UUID ticketId);

    @EntityGraph(attributePaths = {
            "category",
            "createdBy",
            "assignedTo",
            "assignedTeam"
    })
    List<Ticket> findByCreatedBy_UserId(UUID userId);

    @Override
    @EntityGraph(attributePaths = {
            "category",
            "createdBy",
            "assignedTo",
            "assignedTeam"
    })
    List<Ticket> findAll();

    List<Ticket> findByStatus(TicketStatus status);
}
