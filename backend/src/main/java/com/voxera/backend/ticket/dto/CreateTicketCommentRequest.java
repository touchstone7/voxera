package com.voxera.backend.ticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTicketCommentRequest(
        @NotBlank
        @Size(max = 5000)
        String content
) {
}
