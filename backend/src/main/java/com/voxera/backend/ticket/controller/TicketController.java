package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.dto.AssignTicketRequest;
import com.voxera.backend.ticket.dto.CreateTicketRequest;
import com.voxera.backend.ticket.dto.TicketResponse;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.voxera.backend.ticket.dto.AssignTicketRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse createTicket(
            @Valid @RequestBody CreateTicketRequest request) {

        Ticket ticket = ticketService.createTicket(
                request.title(),
                request.description(),
                request.priority(),
                request.category(),
                request.createdBy()
        );

        return TicketResponse.from(ticket);
    }

    @GetMapping("/{ticketId}")
    public TicketResponse getTicket(@PathVariable UUID ticketId) {
        return TicketResponse.from(ticketService.getTicket(ticketId));
    }

    @GetMapping
    public List<TicketResponse> getTickets(
            @RequestParam UUID createdBy) {

        return ticketService.getTicketsForUser(createdBy)
                .stream()
                .map(TicketResponse::from)
                .toList();
    }

    @PostMapping("/{ticketId}/start")
    public TicketResponse startProgress(@PathVariable UUID ticketId) {
        return TicketResponse.from(
                ticketService.startProgress(ticketId));
    }

    @PostMapping("/{ticketId}/pending")
    public TicketResponse markPending(@PathVariable UUID ticketId) {
        return TicketResponse.from(
                ticketService.markPending(ticketId));
    }

    @PostMapping("/{ticketId}/resolve")
    public TicketResponse resolveTicket(@PathVariable UUID ticketId) {
        return TicketResponse.from(
                ticketService.resolveTicket(ticketId));
    }

    @PostMapping("/{ticketId}/close")
    public TicketResponse closeTicket(@PathVariable UUID ticketId) {
        return TicketResponse.from(
                ticketService.closeTicket(ticketId));
    }

    @PostMapping("/{ticketId}/cancel")
    public TicketResponse cancelTicket(@PathVariable UUID ticketId) {
        return TicketResponse.from(
                ticketService.cancelTicket(ticketId));
    }

    @PostMapping("/{ticketId}/assign")
    public ResponseEntity<TicketResponse> assignTicket(
            @PathVariable UUID ticketId,
            @Valid @RequestBody AssignTicketRequest request) {

        Ticket ticket = ticketService.assignTicket(
                ticketId,
                request.assignedTo()
        );

        return ResponseEntity.ok(TicketResponse.from(ticket));
    }
}
