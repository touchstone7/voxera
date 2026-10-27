package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.dto.CreateTicketCommentRequest;
import com.voxera.backend.ticket.dto.TicketCommentResponse;
import com.voxera.backend.ticket.service.TicketCommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets/{ticketId}/comments")
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    public TicketCommentController(
            TicketCommentService ticketCommentService) {
        this.ticketCommentService = ticketCommentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketCommentResponse addComment(
            @PathVariable UUID ticketId,
            @Valid @RequestBody CreateTicketCommentRequest request) {
        return ticketCommentService.addComment(
                ticketId,
                request.content()
        );
    }

    @GetMapping
    public List<TicketCommentResponse> getComments(
            @PathVariable UUID ticketId) {
        return ticketCommentService.getComments(ticketId);
    }
}
