package com.voxera.backend.ticket.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "ticket_categories", uniqueConstraints = {
        @UniqueConstraint(name = "uk_ticket_categories_name_key", columnNames = "name_key")
})
public class TicketCategory {

    @Id
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "name_key", nullable = false, length = 100)
    private String nameKey;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected TicketCategory() {
    }

    public TicketCategory(
            UUID categoryId,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.categoryId = categoryId;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameKey = normalizeName(name);
    }

    @PrePersist
    protected void onCreate() {
        this.nameKey = normalizeName(name);

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.nameKey = normalizeName(name);
        this.updatedAt = LocalDateTime.now();
    }

    private static String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}