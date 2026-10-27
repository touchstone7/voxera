package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketCommentIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    @Test
    void employeeCanCreateAndRetrieveCommentsOnOwnTicket()
            throws Exception {
        createTicket("Comment workflow test");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        mockMvc.perform(authenticated(
                        post("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"content":"First comment"}
                                        """)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticketId")
                        .value(ticket.getTicketId().toString()))
                .andExpect(jsonPath("$.authorId")
                        .value(SEEDED_USER_ID.toString()))
                .andExpect(jsonPath("$.authorName").isNotEmpty())
                .andExpect(jsonPath("$.content").value("First comment"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty());

        mockMvc.perform(authenticated(
                        post("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"content":"Second comment"}
                                        """)))
                .andExpect(status().isCreated());

        mockMvc.perform(authenticated(
                        get("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].content").value("First comment"))
                .andExpect(jsonPath("$[1].content").value("Second comment"));
    }

    @Test
    void agentCanReadCommentsOnTicketRegardlessOfAssignment()
            throws Exception {
        createTicket("Agent conversation access", accessToken);

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        User assignee = createTestUser(UserRole.AGENT);
        ticket.assignTo(assignee);
        ticketRepository.save(ticket);

        String agentToken = createAgentToken();

        mockMvc.perform(authenticated(
                        get("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId()),
                        agentToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void employeeCannotReadCommentsOnAnotherUsersTicket()
            throws Exception {
        String agentToken = createAgentToken();
        createTicket("Another user's ticket", agentToken);

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        mockMvc.perform(authenticated(
                        get("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId())))
                .andExpect(status().isForbidden());
    }

    @Test
    void blankCommentShouldBeRejected() throws Exception {
        createTicket("Blank comment test");

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        mockMvc.perform(authenticated(
                        post("/api/v1/tickets/{ticketId}/comments",
                                ticket.getTicketId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"content":"   "}
                                        """)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void commentOnUnknownTicketShouldReturnNotFound()
            throws Exception {
        mockMvc.perform(authenticated(
                        get("/api/v1/tickets/{ticketId}/comments",
                                java.util.UUID.randomUUID())))
                .andExpect(status().isNotFound());
    }
}
