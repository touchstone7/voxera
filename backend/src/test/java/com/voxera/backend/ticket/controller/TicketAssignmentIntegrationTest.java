package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketAssignmentIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    @Test
    void assignTicket_shouldAssignUserAndPersistAssignment()
            throws Exception {

        UUID agentId = createTestUser();

        createTicket(
                "Laptop issue",
                SEEDED_USER_ID
        );

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        String requestBody = """
                {
                    "assignedTo": "%s"
                }
                """.formatted(agentId);

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/assign",
                                        ticket.getTicketId())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketId")
                        .value(ticket.getTicketId().toString()))
                .andExpect(jsonPath("$.assignedTo")
                        .value(agentId.toString()));

        Ticket persistedTicket = ticketRepository
                .findById(ticket.getTicketId())
                .orElseThrow();

        assertNotNull(persistedTicket.getAssignedTo());

        assertEquals(
                agentId,
                persistedTicket.getAssignedTo().getUserId());
    }

    @Test
    void assignTicket_shouldReturn404ForUnknownUser()
            throws Exception {

        createTicket(
                "Laptop issue",
                SEEDED_USER_ID
        );

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        UUID unknownUserId = UUID.randomUUID();

        String requestBody = """
                {
                    "assignedTo": "%s"
                }
                """.formatted(unknownUserId);

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/assign",
                                        ticket.getTicketId())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error")
                        .value("Not Found"))
                .andExpect(jsonPath("$.message",
                        containsString("User not found")));
    }

    @Test
    void assignTicket_shouldRejectMissingAssignedUser()
            throws Exception {

        createTicket(
                "Laptop issue",
                SEEDED_USER_ID
        );

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        String requestBody = """
                {
                    "assignedTo": null
                }
                """;

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/assign",
                                        ticket.getTicketId())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isBadRequest());
    }
}