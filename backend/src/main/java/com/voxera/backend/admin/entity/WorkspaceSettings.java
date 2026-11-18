package com.voxera.backend.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(
            String workspaceName,
            String organization,
            String timezone,
            String ticketPrefix) {

        this.workspaceName = workspaceName;
        this.organization = organization;
        this.timezone = timezone;
        this.ticketPrefix = ticketPrefix;
        this.updatedAt = LocalDateTime.now();
    }
}