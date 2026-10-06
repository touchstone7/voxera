package com.voxera.backend.security.authentication.dto;

import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String employeeId,
        String name,
        String email,
        String role,
        String accessToken) {
}