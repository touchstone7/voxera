
package com.voxera.backend.admin.service;

import com.voxera.backend.admin.dto.UpdateWorkspaceSettingsRequest;
import com.voxera.backend.admin.entity.WorkspaceSettings;
import com.voxera.backend.admin.repository.WorkspaceSettingsRepository;
import com.voxera.backend.ticket.enums.TicketPriority;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Locale;

@Service
public class WorkspaceSettingsService {

    private static final Integer SETTINGS_ID = 1;

    private final WorkspaceSettingsRepository settingsRepository;

    public WorkspaceSettingsService(
            WorkspaceSettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Transactional(readOnly = true)
    public WorkspaceSettings getSettings() {
        return settingsRepository.findById(SETTINGS_ID)
                .orElseThrow(() -> new IllegalStateException(
                        "Workspace settings record is missing"));
    }

    @Transactional
    public WorkspaceSettings updateSettings(
            UpdateWorkspaceSettingsRequest request) {

        if (!request.hasUpdates()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "At least one setting must be provided");
        }

        WorkspaceSettings settings = getSettings();

        String workspaceName = request.workspaceName() != null
                ? request.workspaceName().trim()
                : settings.getWorkspaceName();

        String organization = request.organization() != null
                ? request.organization().trim()
                : settings.getOrganization();

        String timezone = request.timezone() != null
                ? request.timezone().trim()
                : settings.getTimezone();

        String ticketPrefix = request.ticketPrefix() != null
                ? request.ticketPrefix().trim().toUpperCase(Locale.ROOT)
                : settings.getTicketPrefix();

        TicketPriority defaultPriority = request.defaultPriority() != null
                ? request.defaultPriority()
                : settings.getDefaultPriority();

        validateTimezone(timezone);

        settings.update(
                workspaceName,
                organization,
                timezone,
                ticketPrefix,
                defaultPriority);

        return settings;
    }

    @Transactional(readOnly = true)
    public String getTicketPrefix() {
        return getSettings().getTicketPrefix();
    }

    @Transactional(readOnly = true)
    public TicketPriority getDefaultPriority() {
        return getSettings().getDefaultPriority();
    }

    private void validateTimezone(String timezone) {
        try {
            ZoneId.of(timezone);
        } catch (DateTimeException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid timezone identifier");
        }
    }
}
