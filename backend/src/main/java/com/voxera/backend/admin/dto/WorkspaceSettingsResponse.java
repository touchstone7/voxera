
package com.voxera.backend.admin.dto;

import com.voxera.backend.admin.entity.WorkspaceSettings;
import com.voxera.backend.ticket.enums.TicketPriority;

import java.time.LocalDateTime;

public record WorkspaceSettingsResponse(
        String workspaceName,
        String organization,
        String timezone,
        String ticketPrefix,
        TicketPriority defaultPriority,
        boolean requireAssignment,
        boolean allowReopening,
        LocalDateTime updatedAt) {

    public static WorkspaceSettingsResponse from(WorkspaceSettings settings) {
        return new WorkspaceSettingsResponse(
                settings.getWorkspaceName(),
                settings.getOrganization(),
                settings.getTimezone(),
                settings.getTicketPrefix(),
                settings.getDefaultPriority(),
                settings.isRequireAssignment(),
                settings.isAllowReopening(),
                settings.getUpdatedAt());
    }
}
