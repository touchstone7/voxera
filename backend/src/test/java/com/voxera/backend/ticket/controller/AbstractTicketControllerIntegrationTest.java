package com.voxera.backend.ticket.controller;

import com.jayway.jsonpath.JsonPath;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
abstract class AbstractTicketControllerIntegrationTest {

    protected static final UUID SEEDED_USER_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TicketRepository ticketRepository;

    @Autowired
    protected UserRepository userRepository;

    protected String accessToken;

    @BeforeEach
    void setUp() throws Exception {
        ticketRepository.deleteAll();
        accessToken = loginAndGetAccessToken();
    }

    protected MockHttpServletRequestBuilder authenticated(
            MockHttpServletRequestBuilder request) {

        return request.header(
                "Authorization",
                "Bearer " + accessToken
        );
    }

    protected void createTicket(
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

    protected String createTicketRequest(
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

    protected UUID createTestUser() {

        UUID userId = UUID.randomUUID();
        String employeeId = "TEST-" + userId;

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
}