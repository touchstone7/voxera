package com.voxera.backend.user.dto;

import com.voxera.backend.user.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRoleRequest(
        @NotNull(message = "Role is required") UserRole role) {
}