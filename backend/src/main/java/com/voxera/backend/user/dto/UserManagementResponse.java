package com.voxera.backend.user.dto;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public record UserManagementResponse(
        UUID id,
        String name,
        String email,
        String initials,
        String department,
        UserRole role,
        UserStatus status,
        String joined) {

    private static final DateTimeFormatter JOINED_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);

    public static UserManagementResponse from(User user) {
        return new UserManagementResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                getInitials(user.getName()),
                user.getDepartment(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt().format(JOINED_FORMAT));
    }

    private static String getInitials(String name) {
        if (name == null || name.isBlank()) {
            return "";
        }

        String[] parts = name.trim().split("\\s+");

        if (parts.length == 1) {
            return parts[0].substring(0, 1).toUpperCase(Locale.ROOT);
        }

        return (parts[0].substring(0, 1)
                + parts[parts.length - 1].substring(0, 1)).toUpperCase(Locale.ROOT);
    }
}