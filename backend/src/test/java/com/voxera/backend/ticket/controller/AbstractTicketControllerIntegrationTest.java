package com.voxera.backend.ticket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.jayway.jsonpath.JsonPath;
import com.voxera.backend.security.authentication.PasswordService;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.entity.UserCredential;
import com.voxera.backend.user.repository.UserCredentialRepository;
import com.voxera.backend.user.repository.UserRepository;

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

    @Autowired
    protected UserCredentialRepository userCredentialRepository;

    @Autowired
    protected PasswordService passwordService;

    protected String accessToken;

    @BeforeEach
    void setUp() throws Exception {
        ticketRepository.deleteAll();
        accessToken = loginAndGetAccessToken(
            "emp001@voxera.local",
            "VoxeraDev123!");
    }

    //overloaded function to support other users than the seeded user
    protected MockHttpServletRequestBuilder authenticated(
        MockHttpServletRequestBuilder request,
        String token) {

    return request.header(
            "Authorization",
            "Bearer " + token
        );
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
            String token
    ) throws Exception {

        String requestBody = createTicketRequest(
                title,
                "Integration test description",
                "HIGH",
                "NETWORK"
        );

        mockMvc.perform(
                        authenticated(
                                post("/api/v1/tickets")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody),
                                token))
                .andExpect(status().isCreated());
    }

    protected void createTicket(String title) throws Exception{

        String requestBody = createTicketRequest(
                title,
                "Integration test description",
                "HIGH",
                "NETWORK"
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
            String category) {

        return """
                {
                    "title": "%s",
                    "description": "%s",
                    "priority": "%s",
                    "category": "%s"
                }
                """.formatted(
                title,
                description,
                priority,
                category
        );
    }

    protected User createTestUser() {

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

        return user;
    }

    protected void createTestUserCredentials(
            UUID userId,
            String rawPassword
    ) {
        String passwordHash = passwordService.hash(rawPassword);
        LocalDateTime now = LocalDateTime.now();
        UserCredential credential = new UserCredential(
                userId,
                passwordHash,
                now,
                now
        );

        userCredentialRepository.save(credential);
    }

    protected String loginAndGetAccessToken(
        String email,String password ) throws Exception {

        String requestBody = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(email, password);

        MvcResult result = mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andReturn();

        return JsonPath.read(
                result.getResponse().getContentAsString(),
                "$.accessToken"
        );
    }
}