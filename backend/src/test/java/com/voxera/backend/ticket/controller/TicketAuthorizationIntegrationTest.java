package com.voxera.backend.ticket.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketAuthorizationIntegrationTest extends
 AbstractTicketControllerIntegrationTest{

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

    @Test
    void employeeCannotReadAnotherEmployeesTicket() throws Exception {

        // Employee A creates a ticket
        createTicket("VPN access issue");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        // Create Employee B with an explicit role
        User employeeB = createTestUser(UserRole.EMPLOYEE);

        createTestUserCredentials(
                employeeB.getUserId(),
                "TestPassword123!"
        );

        String employeeBToken = loginAndGetAccessToken(
                employeeB.getEmail(),
                "TestPassword123!"
        );

        // Employee B attempts to read Employee A's ticket
        mockMvc.perform(
                        authenticated(
                                get("/api/v1/tickets/{ticketId}",
                                        ticket.getTicketId()),
                                employeeBToken
                        ))
                .andExpect(status().isForbidden());
    }
}