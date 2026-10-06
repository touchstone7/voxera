package com.voxera.backend.ticket.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketAuthorizationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ticketEndpoint_shouldRejectUnauthenticatedRequest()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/tickets/{ticketId}",
                                UUID.randomUUID()))
                .andExpect(status().isUnauthorized());
    }
}