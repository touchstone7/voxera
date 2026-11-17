package com.voxera.backend.user.controller;

import com.jayway.jsonpath.JsonPath;
import com.voxera.backend.security.authentication.PasswordService;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.entity.UserCredential;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;
import com.voxera.backend.user.repository.UserCredentialRepository;
import com.voxera.backend.user.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminUserControllerIntegrationTest {

    private static final String TEST_PASSWORD = "UserTest123!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordService passwordService;

    private User admin;
    private User agent;
    private User employee;
    private User inactiveEmployee;

    private String adminToken;
    private String agentToken;
    private String employeeToken;

    private String unique;

    @BeforeEach
    void setUp() throws Exception {
        unique = UUID.randomUUID().toString();

        admin = createUser(
                "Admin-" + unique,
                "admin-" + unique + "@test.local",
                "Administration-" + unique,
                UserRole.ADMIN,
                UserStatus.ACTIVE);

        agent = createUser(
                "Agent-" + unique,
                "agent-" + unique + "@test.local",
                "Support-" + unique,
                UserRole.AGENT,
                UserStatus.ACTIVE);

        employee = createUser(
                "Employee-" + unique,
                "employee-" + unique + "@test.local",
                "Operations-" + unique,
                UserRole.EMPLOYEE,
                UserStatus.ACTIVE);

        inactiveEmployee = createUser(
                "Inactive-" + unique,
                "inactive-" + unique + "@test.local",
                "InactiveDepartment-" + unique,
                UserRole.EMPLOYEE,
                UserStatus.INACTIVE);

        adminToken = login(admin);
        agentToken = login(agent);
        employeeToken = login(employee);
    }

    @Test
    void getUsers_adminShouldReturnRealDatabaseUsers() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",
                        hasItem(admin.getUserId().toString())))
                .andExpect(jsonPath("$[*].id",
                        hasItem(agent.getUserId().toString())))
                .andExpect(jsonPath("$[*].id",
                        hasItem(employee.getUserId().toString())))
                .andExpect(jsonPath("$[*].id",
                        hasItem(inactiveEmployee.getUserId().toString())));
    }

    @Test
    void getUsers_agentShouldReceiveForbidden() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .header("Authorization", bearer(agentToken)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getUsers_employeeShouldReceiveForbidden() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .header("Authorization", bearer(employeeToken)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getUsers_shouldSearchByName() throws Exception {
        assertSearchResult("Employee-" + unique, employee);
    }

    @Test
    void getUsers_shouldSearchByEmail() throws Exception {
        assertSearchResult(
                "employee-" + unique + "@test.local",
                employee);
    }

    @Test
    void getUsers_shouldSearchByEmployeeId() throws Exception {
        assertSearchResult(employee.getEmployeeId(), employee);
    }

    @Test
    void getUsers_shouldSearchByDepartment() throws Exception {
        assertSearchResult("Operations-" + unique, employee);
    }

    @Test
    void getUsers_shouldFilterByRole() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("role", "AGENT")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",
                        hasItem(agent.getUserId().toString())))
                .andExpect(jsonPath("$[*].id",
                        not(hasItem(employee.getUserId().toString()))))
                .andExpect(jsonPath("$[*].id",
                        not(hasItem(admin.getUserId().toString()))));
    }

    @Test
    void getUsers_shouldFilterByStatus() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("status", "INACTIVE")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",
                        hasItem(inactiveEmployee.getUserId().toString())))
                .andExpect(jsonPath("$[*].id",
                        not(hasItem(employee.getUserId().toString()))));
    }

    @Test
    void getUsers_shouldApplyCombinedFilters() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("search", "Support-" + unique)
                        .param("role", "AGENT")
                        .param("status", "ACTIVE")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id")
                        .value(agent.getUserId().toString()));
    }

    @Test
    void getUsers_shouldReturnExpectedResponseFields() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("search", "Employee-" + unique)
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id")
                        .value(employee.getUserId().toString()))
                .andExpect(jsonPath("$[0].name")
                        .value(employee.getName()))
                .andExpect(jsonPath("$[0].email")
                        .value(employee.getEmail()))
                .andExpect(jsonPath("$[0].initials").value("E"))
                .andExpect(jsonPath("$[0].department")
                        .value(employee.getDepartment()))
                .andExpect(jsonPath("$[0].role").value("EMPLOYEE"))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"))
                .andExpect(jsonPath("$[0].joined").isNotEmpty());
    }

    @Test
    void getUsers_invalidRoleShouldReturnBadRequest() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("role", "INVALID")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUsers_invalidStatusShouldReturnBadRequest() throws Exception {
        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("status", "INVALID")
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isBadRequest());
    }

    private void assertSearchResult(String search, User expected)
            throws Exception {

        mockMvc.perform(
                get("/api/v1/admin/users")
                        .param("search", search)
                        .header("Authorization", bearer(adminToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",
                        hasItem(expected.getUserId().toString())));
    }

    private User createUser(
            String name,
            String email,
            String department,
            UserRole role,
            UserStatus status) {

        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                userId,
                "UM-" + userId,
                name,
                email,
                department,
                role,
                status,
                now,
                now);

        userRepository.save(user);

        if (status == UserStatus.ACTIVE) {
            UserCredential credential = new UserCredential(
                    userId,
                    passwordService.hash(TEST_PASSWORD),
                    now,
                    now);

            userCredentialRepository.save(credential);
        }

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
}