package com.voxera.backend.ticket.dto;

import com.voxera.backend.ticket.entity.TicketComment;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketCommentResponse(
        UUID commentId,
        UUID ticketId,
        UUID authorId,
        String authorName,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TicketCommentResponse from(TicketComment comment) {
        return new TicketCommentResponse(
                comment.getCommentId(),
                comment.getTicket().getTicketId(),
                comment.getAuthor().getUserId(),
                comment.getAuthor().getName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
