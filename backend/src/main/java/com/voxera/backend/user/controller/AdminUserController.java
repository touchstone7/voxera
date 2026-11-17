package com.voxera.backend.user.controller;

import com.voxera.backend.user.dto.UserManagementResponse;
import com.voxera.backend.user.dto.UpdateUserRoleRequest;
import com.voxera.backend.user.dto.UpdateUserStatusRequest;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;
import com.voxera.backend.user.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserManagementResponse> getUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UserRole role,
            @RequestParam(required = false) UserStatus status) {

        return userService.getUsers(search, role, status)
                .stream()
                .map(UserManagementResponse::from)
                .toList();
    }

    @PatchMapping("/{userId}/role")
    public ResponseEntity<UserManagementResponse> updateUserRole(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserRoleRequest request) {

        return ResponseEntity.ok(
                UserManagementResponse.from(
                        userService.changeUserRole(userId, request.role())));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserManagementResponse> updateUserStatus(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserStatusRequest request) {

        return ResponseEntity.ok(
                UserManagementResponse.from(
                        userService.changeUserStatus(userId, request.status())));
    }
}