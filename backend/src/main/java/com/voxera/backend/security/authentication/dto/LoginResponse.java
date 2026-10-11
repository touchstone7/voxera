package com.voxera.backend.security.authentication.dto;

import java.util.UUID;

import com.voxera.backend.user.enums.UserRole;

public record LoginResponse(
        UUID userId,
        String employeeId,
        String name,
        String email,
        UserRole role,
        String accessToken) {
}