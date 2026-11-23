package com.voxera.backend.admin.controller;

import com.jayway.jsonpath.JsonPath;
import com.voxera.backend.security.authentication.PasswordService;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.entity.UserCredential;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;
import com.voxera.backend.user.repository.UserCredentialRepository;
import com.voxera.backend.user.repository.UserRepository;
import com.voxera.backend.admin.repository.WorkspaceSettingsRepository;
import com.voxera.backend.admin.entity.WorkspaceSettings;

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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminSettingsControllerIntegrationTest {

    private static final String TEST_PASSWORD = "SettingsTest123!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private WorkspaceSettingsRepository workspaceSettingsRepository;

    @Autowired
    private PasswordService passwordService;

    private String adminToken;
    private String agentToken;
    private String employeeToken;

    @BeforeEach
    void setUp() throws Exception {

        WorkspaceSettings settings = workspaceSettingsRepository.findById(1)
                .orElseThrow();

        settings.update(
                "Voxera Support",
                "Voxera Technologies",
                "Asia/Kolkata",
                "VXR",
                TicketPriority.MEDIUM,
                false,
                false);

        workspaceSettingsRepository.save(settings);
        workspaceSettingsRepository.flush();

        String unique = UUID.randomUUID().toString();

        adminToken = login(createUser(
                "Admin-" + unique,
                "admin-" + unique + "@test.local",
                UserRole.ADMIN));

        agentToken = login(createUser(
                "Agent-" + unique,
                "agent-" + unique + "@test.local",
                UserRole.AGENT));

        employeeToken = login(createUser(
                "Employee-" + unique,
                "employee-" + unique + "@test.local",
                UserRole.EMPLOYEE));
    }

    @Test
    void getSettings_adminShouldReturnPersistedDefaults() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.workspaceName",
                        is("Voxera Support")))
                .andExpect(jsonPath("$.organization",
                        is("Voxera Technologies")))
                .andExpect(jsonPath("$.timezone",
                        is("Asia/Kolkata")))
                .andExpect(jsonPath("$.ticketPrefix",
                        is("VXR")))
                .andExpect(jsonPath("$.requireAssignment").value(false))
                .andExpect(jsonPath("$.allowReopening").value(false));
    }

    @Test
    void getSettings_agentShouldReceiveForbidden() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/settings")
                        .header("Authorization", bearer(agentToken)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getSettings_employeeShouldReceiveForbidden() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/settings")
                        .header("Authorization", bearer(employeeToken)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateSettings_adminShouldUpdateOnlyProvidedFields()
            throws Exception {

        String requestBody = """
                {
                    "ticketPrefix": "it"
                }
                """;

        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketPrefix", is("IT")))
                .andExpect(jsonPath("$.workspaceName",
                        is("Voxera Support")))
                .andExpect(jsonPath("$.organization",
                        is("Voxera Technologies")))
                .andExpect(jsonPath("$.timezone",
                        is("Asia/Kolkata")));
    }

    @Test
    void updateSettings_shouldPersistChanges() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "workspaceName": "Voxera Enterprise"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.workspaceName",
                        is("Voxera Enterprise")));
    }

    @Test
    void updateSettings_agentShouldReceiveForbidden() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(agentToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticketPrefix": "IT"
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateSettings_employeeShouldReceiveForbidden()
            throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(employeeToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticketPrefix": "IT"
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateSettings_shouldRejectInvalidTimezone() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "timezone": "Invalid/Timezone"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSettings_shouldRejectInvalidPrefix() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticketPrefix": "INVALID-PREFIX"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSettings_shouldRejectEmptyRequest() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatedTicketPrefixShouldBeUsedWhenCreatingTickets()
            throws Exception {

        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticketPrefix": "IT"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketPrefix", is("IT")));

        mockMvc.perform(
                post("/api/v1/tickets")
                        .header("Authorization", bearer(employeeToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Prefix integration test",
                                    "description": "Verify configured ticket prefix",
                                    "priority": "HIGH",
                                    "category": "a1000000-0000-4000-8000-000000000002"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticketNumber",
                        org.hamcrest.Matchers.startsWith("IT-")));
    }

    @Test
    void updateSettings_shouldUpdateDefaultPriority() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "defaultPriority": "HIGH"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.defaultPriority", is("HIGH")));

        mockMvc.perform(
                get("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.defaultPriority", is("HIGH")));
    }

    @Test
    void updateSettings_shouldRejectInvalidDefaultPriority() throws Exception {
        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "defaultPriority": "URGENT"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSettings_shouldPreserveDefaultPriorityWhenOmitted()
            throws Exception {

        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "defaultPriority": "HIGH"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(
                patch("/api/v1/admin/settings")
                        .header("Authorization", bearer(adminToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticketPrefix": "IT"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.defaultPriority", is("HIGH")))
                .andExpect(jsonPath("$.ticketPrefix", is("IT")));
    }

    @Test
    void adminCanUpdateWorkflowSettings() throws Exception {
        String requestBody = """
                {
                    "requireAssignment": true,
                    "allowReopening": true
                }
                """;

        mockMvc.perform(authenticated(
                patch("/api/v1/admin/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody),
                adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requireAssignment").value(true))
                .andExpect(jsonPath("$.allowReopening").value(true));

        mockMvc.perform(authenticated(
                get("/api/v1/admin/settings"),
                adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requireAssignment").value(true))
                .andExpect(jsonPath("$.allowReopening").value(true));
    }

    @Test
    void partialWorkflowSettingsUpdateShouldPreserveOmittedValues() throws Exception {
        String initialRequest = """
                {
                    "requireAssignment": true,
                    "allowReopening": true
                }
                """;

        mockMvc.perform(authenticated(
                patch("/api/v1/admin/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(initialRequest),
                adminToken))
                .andExpect(status().isOk());

        String partialRequest = """
                {
                    "requireAssignment": false
                }
                """;

        mockMvc.perform(authenticated(
                patch("/api/v1/admin/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partialRequest),
                adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requireAssignment").value(false))
                .andExpect(jsonPath("$.allowReopening").value(true));
    }

    private User createUser(
            String name,
            String email,
            UserRole role) {

        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                userId,
                "SET-" + userId,
                name,
                email,
                "Testing",
                role,
                UserStatus.ACTIVE,
                now,
                now);

        userRepository.save(user);

        userCredentialRepository.save(new UserCredential(
                userId,
                passwordService.hash(TEST_PASSWORD),
                now,
                now));

        return user;
    }

    private String login(User user) throws Exception {
        String requestBody = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(user.getEmail(), TEST_PASSWORD);

        MvcResult result = mockMvc.perform(
                post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        return JsonPath.read(
                result.getResponse().getContentAsString(),
                "$.accessToken");
    }

    private String bearer(String token) {
        return "Bearer " + token;
    }

    private MockHttpServletRequestBuilder authenticated(
        MockHttpServletRequestBuilder request,
        String token) {

    return request.header(
            "Authorization",
            "Bearer " + token
    );
    }
}