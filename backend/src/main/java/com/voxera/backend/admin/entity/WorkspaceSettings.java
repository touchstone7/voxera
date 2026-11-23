
package com.voxera.backend.admin.entity;

import com.voxera.backend.ticket.enums.TicketPriority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.LocalDateTime;

@Entity
@Table(name = "workspace_settings")
public class WorkspaceSettings {

    @Id
    @Column(name = "settings_id")
    private Integer id;

    @Column(name = "workspace_name", nullable = false, length = 150)
    private String workspaceName;

    @Column(nullable = false, length = 150)
    private String organization;

    @Column(nullable = false, length = 100)
    private String timezone;

    @Column(name = "ticket_prefix", nullable = false, length = 10)
    private String ticketPrefix;

    @Enumerated(EnumType.STRING)
    @Column(name = "default_priority", nullable = false, length = 20)
    private TicketPriority defaultPriority;

    @Column(name = "require_assignment", nullable = false)
    private boolean requireAssignment = false;

    @Column(name = "allow_reopening", nullable = false)
    private boolean allowReopening = false;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    protected WorkspaceSettings() {
    }

    public Integer getId() {
        return id;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public String getOrganization() {
        return organization;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTicketPrefix() {
        return ticketPrefix;
    }

    public TicketPriority getDefaultPriority() {
        return defaultPriority;
    }

    public boolean isRequireAssignment() {
        return requireAssignment;
    }

    public boolean isAllowReopening() {
        return allowReopening;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Retained for existing callers that do not update workflow settings.
    public void update(
            String workspaceName,
            String organization,
            String timezone,
            String ticketPrefix,
            TicketPriority defaultPriority) {

        update(
                workspaceName,
                organization,
                timezone,
                ticketPrefix,
                defaultPriority,
                requireAssignment,
                allowReopening);
    }

    public void update(
            String workspaceName,
            String organization,
            String timezone,
            String ticketPrefix,
            TicketPriority defaultPriority,
            boolean requireAssignment,
            boolean allowReopening) {

        this.workspaceName = workspaceName;
        this.organization = organization;
        this.timezone = timezone;
        this.ticketPrefix = ticketPrefix;
        this.defaultPriority = defaultPriority;
        this.requireAssignment = requireAssignment;
        this.allowReopening = allowReopening;
        this.updatedAt = LocalDateTime.now();
    }
}
