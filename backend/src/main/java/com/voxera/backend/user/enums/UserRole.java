package com.voxera.backend.user.enums;

public enum UserRole {
    EMPLOYEE,
    AGENT,
    ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}