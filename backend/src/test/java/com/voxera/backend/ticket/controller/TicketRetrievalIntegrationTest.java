package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketStatus;
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

        createTicket(
                "VPN connection issue",
                SEEDED_USER_ID
        );

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

        UUID secondUserId = createTestUser();

        createTicket(
                "First user's ticket",
                SEEDED_USER_ID
        );

        createTicket(
                "Second user's ticket",
                secondUserId
        );

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