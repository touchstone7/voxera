
package com.voxera.backend.ticket.dto;

import com.voxera.backend.ticket.entity.TicketCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketCategoryResponse(
        UUID categoryId,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static TicketCategoryResponse from(TicketCategory category) {
        return new TicketCategoryResponse(
                category.getCategoryId(),
                category.getName(),
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt());
    }
}
