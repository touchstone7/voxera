package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketCreationIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    @Test
    void createTicket_shouldReturnCreatedTicketAndPersistIt()
            throws Exception {

        String requestBody = createTicketRequest(
                "Laptop not connecting to Wi-Fi",
                "Wi-Fi disconnects every few minutes",
                "HIGH",
                "NETWORK",
                SEEDED_USER_ID
        );

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticketId", notNullValue()))
                .andExpect(jsonPath("$.ticketNumber",
                        startsWith("INC-")))
                .andExpect(jsonPath("$.title")
                        .value("Laptop not connecting to Wi-Fi"))
                .andExpect(jsonPath("$.description")
                        .value("Wi-Fi disconnects every few minutes"))
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.OPEN.name()))
                .andExpect(jsonPath("$.priority")
                        .value(TicketPriority.HIGH.name()))
                .andExpect(jsonPath("$.category")
                        .value(TicketCategory.NETWORK.name()))
                .andExpect(jsonPath("$.createdBy")
                        .value(SEEDED_USER_ID.toString()))
                .andExpect(jsonPath("$.assignedTo")
                        .doesNotExist())
                .andExpect(jsonPath("$.createdAt", notNullValue()));

        Ticket persistedTicket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        assertEquals(
                "Laptop not connecting to Wi-Fi",
                persistedTicket.getTitle());

        assertEquals(
                SEEDED_USER_ID,
                persistedTicket.getCreatedBy().getUserId());
    }

    @Test
    void createTicket_shouldRejectInvalidRequest()
            throws Exception {

        String requestBody = """
                {
                    "title": "",
                    "description": "",
                    "priority": null,
                    "category": null,
                    "createdBy": null
                }
                """;

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isBadRequest());
    }
}