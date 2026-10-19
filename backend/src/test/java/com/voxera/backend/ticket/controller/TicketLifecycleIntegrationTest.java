package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketLifecycleIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    @Test
    void ticketLifecycle_shouldPersistStateTransitions()
            throws Exception {

        createTicket(
                "Production server issue"
        );

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        UUID ticketId = ticket.getTicketId();
        accessToken = createAgentToken();

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/start",
                                        ticketId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.IN_PROGRESS.name()));

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/pending",
                                        ticketId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.PENDING.name()));

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/resolve",
                                        ticketId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.RESOLVED.name()))
                .andExpect(jsonPath("$.resolvedAt",
                        notNullValue()));

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/close",
                                        ticketId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.CLOSED.name()))
                .andExpect(jsonPath("$.closedAt",
                        notNullValue()));

        Ticket persistedTicket = ticketRepository
                .findById(ticketId)
                .orElseThrow();

        assertEquals(
                TicketStatus.CLOSED,
                persistedTicket.getStatus());

        assertNotNull(persistedTicket.getResolvedAt());
        assertNotNull(persistedTicket.getClosedAt());
    }
        @Test
    void employeeCanCancelOwnTicket() throws Exception {
        createTicket("Employee-owned ticket");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/cancel",
                                        ticket.getTicketId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.CANCELLED.name()));

        Ticket persistedTicket = ticketRepository
                .findById(ticket.getTicketId())
                .orElseThrow();

        assertEquals(
                TicketStatus.CANCELLED,
                persistedTicket.getStatus());
    }

    @Test
    void employeeCannotCancelAnotherEmployeesTicket()
            throws Exception {
        createTicket("Another employee's ticket");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        User anotherEmployee = createTestUser(UserRole.EMPLOYEE);

        createTestUserCredentials(
                anotherEmployee.getUserId(),
                "TestPassword123!");

        String anotherEmployeeToken = loginAndGetAccessToken(
                anotherEmployee.getEmail(),
                "TestPassword123!");

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/cancel",
                                        ticket.getTicketId()),
                                anotherEmployeeToken))
                .andExpect(status().isForbidden());

        Ticket persistedTicket = ticketRepository
                .findById(ticket.getTicketId())
                .orElseThrow();

        assertEquals(TicketStatus.OPEN, persistedTicket.getStatus());
    }
}

