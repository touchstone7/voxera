package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.entity.TicketHistory;
import com.voxera.backend.ticket.enums.TicketHistoryAction;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;

import java.util.List;

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
                .andExpect(jsonPath("$.resolvedAt",
                        notNullValue()))
                .andExpect(jsonPath("$.closedAt",
                        notNullValue()))
                .andExpect(jsonPath("$.createdAt",
                        notNullValue()))
                .andExpect(jsonPath("$.updatedAt",
                        notNullValue()));

        Ticket persistedTicket = ticketRepository
                .findById(ticketId)
                .orElseThrow();

        assertEquals(
                TicketStatus.CLOSED,
                persistedTicket.getStatus());

        assertNotNull(persistedTicket.getCreatedAt());
        assertNotNull(persistedTicket.getUpdatedAt());
        assertNotNull(persistedTicket.getResolvedAt());
        assertNotNull(persistedTicket.getClosedAt());

        assertEquals(
                true,
                !persistedTicket.getUpdatedAt()
                        .isBefore(persistedTicket.getCreatedAt()));

        assertEquals(
                true,
                !persistedTicket.getResolvedAt()
                        .isAfter(persistedTicket.getClosedAt()));

                List<TicketHistory> history =
                ticketHistoryRepository
                        .findByTicket_TicketIdOrderByCreatedAtAsc(ticketId);

        assertEquals(5, history.size());

        // Creation + four lifecycle transitions
        assertEquals(
                List.of(
                        TicketHistoryAction.TICKET_CREATED.name(),
                        TicketHistoryAction.STATUS_CHANGED.name(),
                        TicketHistoryAction.STATUS_CHANGED.name(),
                        TicketHistoryAction.STATUS_CHANGED.name(),
                        TicketHistoryAction.STATUS_CHANGED.name()
                ),
                history.stream()
                        .map(TicketHistory::getAction)
                        .toList()
        );

        // Initial ticket creation
        assertEquals("EMPLOYEE", history.get(0).getActorType());
        assertEquals(null, history.get(0).getOldValue());
        assertEquals(TicketStatus.OPEN.name(), history.get(0).getNewValue());

        // Lifecycle transitions
        assertEquals(TicketStatus.OPEN.name(), history.get(1).getOldValue());
        assertEquals(TicketStatus.IN_PROGRESS.name(), history.get(1).getNewValue());

        assertEquals(TicketStatus.IN_PROGRESS.name(), history.get(2).getOldValue());
        assertEquals(TicketStatus.PENDING.name(), history.get(2).getNewValue());

        assertEquals(TicketStatus.PENDING.name(), history.get(3).getOldValue());
        assertEquals(TicketStatus.RESOLVED.name(), history.get(3).getNewValue());

        assertEquals(TicketStatus.RESOLVED.name(), history.get(4).getOldValue());
        assertEquals(TicketStatus.CLOSED.name(), history.get(4).getNewValue());

        // All lifecycle transitions were performed by the agent
        for (int i = 1; i < history.size(); i++) {
        assertEquals("AGENT", history.get(i).getActorType());
        }
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
        assertNotNull(persistedTicket.getCreatedAt());
        assertNotNull(persistedTicket.getUpdatedAt());
        assertEquals(
                true,
                !persistedTicket.getUpdatedAt()
                        .isBefore(persistedTicket.getCreatedAt()));

        List<TicketHistory> history = ticketHistoryRepository
        .findByTicket_TicketIdOrderByCreatedAtAsc(
                        ticket.getTicketId());

        assertEquals(2, history.size());

        assertEquals(
                        TicketHistoryAction.TICKET_CREATED.name(),
                        history.get(0).getAction());

        assertEquals(
                        TicketHistoryAction.STATUS_CHANGED.name(),
                        history.get(1).getAction());

        assertEquals(
                        TicketStatus.OPEN.name(),
                        history.get(1).getOldValue());

        assertEquals(
                        TicketStatus.CANCELLED.name(),
                        history.get(1).getNewValue());

        assertEquals("EMPLOYEE", history.get(1).getActorType());
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

    @Test
    void employeeCannotPerformAgentOnlyLifecycleActions() throws Exception {
        createTicket("Employee lifecycle authorization test");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        String[] actions = {"start", "pending", "resolve", "close"};

        for (String action : actions) {
            mockMvc.perform(
                            authenticated(
                                    post("/api/v1/tickets/{ticketId}/" + action,
                                            ticket.getTicketId())))
                    .andExpect(status().isForbidden());

            Ticket persistedTicket = ticketRepository
                    .findById(ticket.getTicketId())
                    .orElseThrow();

            assertEquals(TicketStatus.OPEN, persistedTicket.getStatus());
        }
    }

}

