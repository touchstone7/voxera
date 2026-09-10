package com.voxera.backend.ticket.entity;

import com.voxera.backend.team.entity.Team;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private UUID ticketId;

    @Column(name = "ticket_number", nullable = false, unique = true, length = 30)
    private String ticketNumber;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TicketCategory category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_team")
    private Team assignedTeam;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    protected Ticket() {
    }

    public Ticket(
            UUID ticketId,
            String ticketNumber,
            String title,
            String description,
            TicketPriority priority,
            TicketCategory category,
            User createdBy,
            LocalDateTime createdAt) {

        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.title = title;
        this.description = description;
        this.status = TicketStatus.OPEN;
        this.priority = priority;
        this.category = category;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.version = 0L;
    }

    public void startProgress() {
        requireStatus(TicketStatus.OPEN);
        this.status = TicketStatus.IN_PROGRESS;
        touch();
    }

    public void markPending() {
        requireStatus(TicketStatus.IN_PROGRESS);
        this.status = TicketStatus.PENDING;
        touch();
    }

    public void resolve() {
        if (status != TicketStatus.IN_PROGRESS && status != TicketStatus.PENDING) {
            throw new IllegalStateException(
                    "Ticket can only be resolved from IN_PROGRESS or PENDING");
        }

        this.status = TicketStatus.RESOLVED;
        this.resolvedAt = LocalDateTime.now();
        touch();
    }

    public void close() {
        requireStatus(TicketStatus.RESOLVED);
        this.status = TicketStatus.CLOSED;
        this.closedAt = LocalDateTime.now();
        touch();
    }

    public void cancel() {
        if (status == TicketStatus.CLOSED || status == TicketStatus.CANCELLED) {
            throw new IllegalStateException(
                    "Closed or cancelled tickets cannot be cancelled");
        }

        this.status = TicketStatus.CANCELLED;
        touch();
    }

    public void assignTo(User user) {
        this.assignedTo = user;
        touch();
    }

    public void assignToTeam(Team team) {
        this.assignedTeam = team;
        touch();
    }

    private void requireStatus(TicketStatus expected) {
        if (status != expected) {
            throw new IllegalStateException(
                    "Expected ticket status " + expected + " but was " + status);
        }
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public Long getVersion() {
        return version;
    }
}
