package com.voxera.backend.ticket.dto;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponse(
        UUID ticketId,
        String ticketNumber,
        String title,
        String description,
        TicketStatus status,
        TicketPriority priority,
        TicketCategory category,
        UUID createdBy,
        UUID assignedTo,
        UUID assignedTeam,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime resolvedAt,
        LocalDateTime closedAt
) {

    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(
                ticket.getTicketId(),
                ticket.getTicketNumber(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCategory(),
                ticket.getCreatedBy().getUserId(),
                ticket.getAssignedTo() != null
                        ? ticket.getAssignedTo().getUserId()
                        : null,
                ticket.getAssignedTeam() != null
                        ? ticket.getAssignedTeam().getTeamId()
                        : null,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getResolvedAt(),
                ticket.getClosedAt()
        );
    }
}
