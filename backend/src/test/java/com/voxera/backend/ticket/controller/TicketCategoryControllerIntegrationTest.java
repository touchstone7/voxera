
package com.voxera.backend.ticket.controller;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TicketCategoryControllerIntegrationTest
        extends AbstractTicketControllerIntegrationTest {

    private String createAdminToken() throws Exception {
        User admin = createTestUser(UserRole.ADMIN);
        String password = "AdminPassword123!";

        createTestUserCredentials(admin.getUserId(), password);

        return loginAndGetAccessToken(admin.getEmail(), password);
    }

    private String uniqueCategoryName() {
        return "Test-" + UUID.randomUUID();
    }

    private String categoryRequest(String name) {
        return """
                {
                    "name": "%s"
                }
                """.formatted(name);
    }

    @Test
    void unauthenticatedUserCannotRetrieveActiveCategories()
            throws Exception {

        mockMvc.perform(get("/api/v1/ticket-categories/active"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void employeeCanRetrieveActiveCategories() throws Exception {
        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories/active")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void agentCanRetrieveActiveCategories() throws Exception {
        String agentToken = createAgentToken();

        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories/active"),
                        agentToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void adminCanRetrieveActiveCategories() throws Exception {
        String adminToken = createAdminToken();

        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories/active"),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void onlyAdminCanRetrieveAllCategories() throws Exception {
        String adminToken = createAdminToken();

        mockMvc.perform(
                authenticated(get("/api/v1/ticket-categories"),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        mockMvc.perform(
                authenticated(get("/api/v1/ticket-categories")))
                .andExpect(status().isForbidden());
    }

    @Test
    void unauthenticatedUserCannotRetrieveAllCategories()
            throws Exception {

        mockMvc.perform(get("/api/v1/ticket-categories"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminCanCreateCategory() throws Exception {
        String adminToken = createAdminToken();
        String name = uniqueCategoryName();

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(name)),
                        adminToken))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void employeeCannotCreateCategory() throws Exception {
        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(uniqueCategoryName()))))
                .andExpect(status().isForbidden());
    }

    @Test
    void createCategoryRejectsBlankName() throws Exception {
        String adminToken = createAdminToken();

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest("   ")),
                        adminToken))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryRejectsNameLongerThan100Characters()
            throws Exception {

        String adminToken = createAdminToken();
        String name = "A".repeat(101);

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(name)),
                        adminToken))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryRejectsDuplicateNameIgnoringCase()
            throws Exception {

        String adminToken = createAdminToken();
        String name = uniqueCategoryName();

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(name)),
                        adminToken))
                .andExpect(status().isCreated());

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(name.toUpperCase())),
                        adminToken))
                .andExpect(status().isConflict());
    }

    @Test
    void adminCanRenameCategory() throws Exception {
        String adminToken = createAdminToken();
        String originalName = uniqueCategoryName();
        String renamedName = uniqueCategoryName();

        String response = mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(originalName)),
                        adminToken))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UUID categoryId = UUID.fromString(
                com.jayway.jsonpath.JsonPath.read(
                        response, "$.categoryId"));

        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/name",
                                categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(renamedName)),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(categoryId.toString()))
                .andExpect(jsonPath("$.name").value(renamedName));
    }

    @Test
    void adminCanDeactivateAndReactivateCategory() throws Exception {
        String adminToken = createAdminToken();
        String name = uniqueCategoryName();

        String response = mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(name)),
                        adminToken))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UUID categoryId = UUID.fromString(
                com.jayway.jsonpath.JsonPath.read(
                        response, "$.categoryId"));

        // Deactivate
        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/deactivate",
                                categoryId),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));

        // Inactive category must not appear in the active list.
        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories/active")))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        org.hamcrest.Matchers.not(
                                org.hamcrest.Matchers.containsString(
                                        categoryId.toString()))));

        // But Admin can still see it in the complete list.
        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories"),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        org.hamcrest.Matchers.containsString(
                                categoryId.toString())));

        // Reactivate
        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/activate",
                                categoryId),
                        adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));

        mockMvc.perform(
                authenticated(
                        get("/api/v1/ticket-categories/active")))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        org.hamcrest.Matchers.containsString(
                                categoryId.toString())));
    }

    @Test
    void renamingCategoryRejectsDuplicateName() throws Exception {
        String adminToken = createAdminToken();

        String firstName = uniqueCategoryName();
        String secondName = uniqueCategoryName();

        mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(firstName)),
                        adminToken))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(
                authenticated(
                        post("/api/v1/ticket-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(secondName)),
                        adminToken))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UUID secondCategoryId = UUID.fromString(
                com.jayway.jsonpath.JsonPath.read(
                        response, "$.categoryId"));

        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/name",
                                secondCategoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest(firstName.toUpperCase())),
                        adminToken))
                .andExpect(status().isConflict());
    }

    @Test
    void operationsReturnNotFoundForUnknownCategory() throws Exception {
        String adminToken = createAdminToken();
        UUID unknownId = UUID.randomUUID();

        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/activate",
                                unknownId),
                        adminToken))
                .andExpect(status().isNotFound());

        mockMvc.perform(
                authenticated(
                        patch("/api/v1/ticket-categories/{id}/name",
                                unknownId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoryRequest("Unknown")),
                        adminToken))
                .andExpect(status().isNotFound());
    }
}
