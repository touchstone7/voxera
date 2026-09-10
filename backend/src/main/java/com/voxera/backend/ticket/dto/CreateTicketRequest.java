package com.voxera.backend.ticket.dto;

import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTicketRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        TicketPriority priority,

        @NotNull
        TicketCategory category,

        @NotNull
        UUID createdBy
) {
}
