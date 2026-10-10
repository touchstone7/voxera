package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.user.entity.User;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketRetrievalIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    @Test
    void getTicket_shouldReturnExistingTicket() throws Exception {

        createTicket("VPN connection issue");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        mockMvc.perform(
                        authenticated(
                                get("/api/v1/tickets/{ticketId}",
                                        ticket.getTicketId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketId")
                        .value(ticket.getTicketId().toString()))
                .andExpect(jsonPath("$.title")
                        .value("VPN connection issue"))
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.OPEN.name()));
    }

    @Test
    void getTicket_shouldReturn404ForUnknownTicket()
            throws Exception {

        UUID unknownTicketId = UUID.randomUUID();

        mockMvc.perform(
                        authenticated(
                                get("/api/v1/tickets/{ticketId}",
                                        unknownTicketId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error")
                        .value("Not Found"))
                .andExpect(jsonPath("$.message",
                        containsString("Ticket not found")));
    }

    @Test
    void getTicketsForUser_shouldReturnTicketsForRequestedUser()
            throws Exception {

    // User A: seeded user
    createTicket("First user's ticket");

    // User B: new test user
    User secondUser = createTestUser();

    createTestUserCredentials(
            secondUser.getUserId(),
            "TestPassword123!"
    );

    // Obtain tokenB using second user's email
    String tokenB = loginAndGetAccessToken(
            secondUser.getEmail(),
            "TestPassword123!"
    );

    // Create ticket as user B
    createTicket("Second user's ticket", tokenB);

        mockMvc.perform(
                        authenticated(
                                get("/api/v1/tickets")
                                        .param(
                                                "createdBy",
                                                SEEDED_USER_ID.toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title")
                        .value("First user's ticket"))
                .andExpect(jsonPath("$[0].createdBy")
                        .value(SEEDED_USER_ID.toString()));
    }
}