package com.voxera.backend.ticket.controller;

import com.jayway.jsonpath.JsonPath;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerIntegrationTest {

    private static final UUID SEEDED_USER_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    private String accessToken;

    @BeforeEach
    void setUp() throws Exception {
        ticketRepository.deleteAll();
        accessToken = loginAndGetAccessToken();
    }

    @Test
    void ticketEndpoint_shouldRejectUnauthenticatedRequest()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/tickets/{ticketId}",
                                UUID.randomUUID()))
                .andExpect(status().isUnauthorized());
    }

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
    void getTicketsForUser_shouldReturnOnlyUsersTickets()
            throws Exception {

        UUID secondUserId = createTestUser("EMP002");

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

    @Test
    void assignTicket_shouldAssignUserAndPersistAssignment()
            throws Exception {

        UUID agentId = createTestUser("EMP003");

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
    void ticketLifecycle_shouldPersistStateTransitions()
            throws Exception {

        createTicket(
                "Production server issue",
                SEEDED_USER_ID
        );

        Ticket ticket = ticketRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        UUID ticketId = ticket.getTicketId();

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
                .andExpect(jsonPath("$.resolvedAt", notNullValue()));

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets/{ticketId}/close",
                                        ticketId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(TicketStatus.CLOSED.name()))
                .andExpect(jsonPath("$.closedAt", notNullValue()));

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

    private String loginAndGetAccessToken() throws Exception {

        MvcResult result = mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "email": "emp001@voxera.local",
                                            "password": "VoxeraDev123!"
                                        }
                                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andReturn();

        return JsonPath.read(
                result.getResponse().getContentAsString(),
                "$.accessToken"
        );
    }

    private MockHttpServletRequestBuilder authenticated(
            MockHttpServletRequestBuilder request) {

        return request.header(
                "Authorization",
                "Bearer " + accessToken
        );
    }

    private void createTicket(
            String title,
            UUID createdBy) throws Exception {

        String requestBody = createTicketRequest(
                title,
                "Integration test description",
                "HIGH",
                "NETWORK",
                createdBy
        );

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)))
                .andExpect(status().isCreated());
    }

    private String createTicketRequest(
            String title,
            String description,
            String priority,
            String category,
            UUID createdBy) {

        return """
                {
                    "title": "%s",
                    "description": "%s",
                    "priority": "%s",
                    "category": "%s",
                    "createdBy": "%s"
                }
                """.formatted(
                title,
                description,
                priority,
                category,
                createdBy
        );
    }

    private UUID createTestUser(String employeeId) {

        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                userId,
                employeeId,
                "Test Agent",
                employeeId.toLowerCase() + "@voxera.local",
                "IT",
                "AGENT",
                "ACTIVE",
                now,
                now
        );

        userRepository.save(user);

        return userId;
    }
}