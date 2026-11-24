
package com.voxera.backend.admin.dto;

import com.voxera.backend.ticket.enums.TicketPriority;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateWorkspaceSettingsRequest(

        @Size(max = 150) @Pattern(regexp = ".*\\S.*", message = "Workspace name must not be blank") String workspaceName,

        @Size(max = 150) @Pattern(regexp = ".*\\S.*", message = "Organization must not be blank") String organization,

        @Size(max = 100) @Pattern(regexp = ".*\\S.*", message = "Timezone must not be blank") String timezone,

        @Pattern(regexp = "(?i)^[a-z0-9]{2,10}$", message = "Ticket prefix must contain 2–10 alphanumeric characters") String ticketPrefix,

        TicketPriority defaultPriority,

        Boolean requireAssignment,

        Boolean allowReopening) {

    public boolean hasUpdates() {
        return workspaceName != null
                || organization != null
                || timezone != null
                || ticketPrefix != null
                || defaultPriority != null
                || requireAssignment != null
                || allowReopening != null;
    }
}
