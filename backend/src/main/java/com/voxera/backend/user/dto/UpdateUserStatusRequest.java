package com.voxera.backend.user.dto;

import com.voxera.backend.user.enums.UserStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateUserStatusRequest(
        @NotNull(message = "Status is required") UserStatus status) {
}