package com.voxera.backend.admin.controller;

import com.voxera.backend.admin.dto.UpdateWorkspaceSettingsRequest;
import com.voxera.backend.admin.dto.WorkspaceSettingsResponse;
import com.voxera.backend.admin.service.WorkspaceSettingsService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/settings")
@PreAuthorize ("hasRole('ADMIN')")
public class AdminSettingsController {

    private final WorkspaceSettingsService settingsService;

    public AdminSettingsController(
            WorkspaceSettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public WorkspaceSettingsResponse getSettings() {
        return WorkspaceSettingsResponse.from(
                settingsService.getSettings());
    }

    @PatchMapping
    public WorkspaceSettingsResponse updateSettings(
            @Valid @RequestBody UpdateWorkspaceSettingsRequest request) {

        return WorkspaceSettingsResponse.from(
                settingsService.updateSettings(request));
    }
}