
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

    private static final int MAX_NAME_LENGTH = 100;

    @Id
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(name = "name_key", nullable = false, length = MAX_NAME_LENGTH)
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
        this.name = normalizeDisplayName(name);
        this.nameKey = normalizeNameKey(this.name);
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.name = normalizeDisplayName(this.name);
        this.nameKey = normalizeNameKey(this.name);

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.name = normalizeDisplayName(this.name);
        this.nameKey = normalizeNameKey(this.name);
        this.updatedAt = LocalDateTime.now();
    }

    public static String nameKeyFor(String name) {
        return normalizeNameKey(normalizeDisplayName(name));
    }

    private static String normalizeDisplayName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Category name cannot be null");
        }

        String normalizedName = name.trim();

        if (normalizedName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Category name cannot be blank");
        }

        if (normalizedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Category name cannot exceed "
                            + MAX_NAME_LENGTH + " characters");
        }

        return normalizedName;
    }

    private static String normalizeNameKey(String name) {
        return name.toLowerCase(Locale.ROOT);
    }

    public void rename(String newName) {
        this.name = normalizeDisplayName(newName);
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
