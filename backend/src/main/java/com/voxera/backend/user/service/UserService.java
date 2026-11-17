package com.voxera.backend.user.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;
import com.voxera.backend.security.CurrentUserService;

import java.util.List;
import java.util.Locale;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CurrentUserService currentUserService;

    public UserService(
            UserRepository userRepository,
            CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    @Transactional(readOnly = true)
    public User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + userId));
    }

    @Transactional(readOnly = true)
    public User getUserByEmployeeId(String employeeId) {
        return userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + employeeId));
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(
            String search,
            UserRole role,
            UserStatus status) {

        String normalizedSearch = search == null || search.isBlank()
                ? null
                : search.trim().toLowerCase(Locale.ROOT);

        return userRepository.findAll()
                .stream()
                .filter(user -> normalizedSearch == null
                        || contains(user.getName(), normalizedSearch)
                        || contains(user.getEmail(), normalizedSearch)
                        || contains(user.getEmployeeId(), normalizedSearch)
                        || contains(user.getDepartment(), normalizedSearch))
                .filter(user -> role == null || user.getRole() == role)
                .filter(user -> status == null || user.getStatus() == status)
                .sorted((first, second) -> second.getCreatedAt().compareTo(first.getCreatedAt()))
                .toList();
    }

    @Transactional
    public User changeUserRole(UUID userId, UserRole newRole) {
        User actor = currentUserService.getCurrentUser();

        lockActiveAdministrators();

        User target = getUser(userId);

        validateAdministrator(actor);
        preventSelfModification(actor, target);

        if (target.getRole() == UserRole.ADMIN
                && target.getStatus() == UserStatus.ACTIVE
                && newRole != UserRole.ADMIN) {
            ensureAnotherActiveAdministratorExists(target);
        }

        if (target.getRole() != newRole) {
            target.changeRole(newRole);
        }

        return userRepository.save(target);
    }

    @Transactional
    public User changeUserStatus(UUID userId, UserStatus newStatus) {
        User actor = currentUserService.getCurrentUser();

        lockActiveAdministrators();

        User target = getUser(userId);

        validateAdministrator(actor);
        preventSelfModification(actor, target);

        if (target.getRole() == UserRole.ADMIN
                && target.getStatus() == UserStatus.ACTIVE
                && newStatus == UserStatus.INACTIVE) {
            ensureAnotherActiveAdministratorExists(target);
        }

        if (target.getStatus() != newStatus) {
            target.changeStatus(newStatus);
        }

        return userRepository.save(target);
    }

    private void lockActiveAdministrators() {
        userRepository.findByRoleAndStatusForUpdate(
                UserRole.ADMIN,
                UserStatus.ACTIVE);
    }

    private void validateAdministrator(User actor) {
        if (actor.getRole() != UserRole.ADMIN
                || actor.getStatus() != UserStatus.ACTIVE) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "An active administrator is required");
        }
    }

    private void preventSelfModification(User actor, User target) {
        if (actor.getUserId().equals(target.getUserId())) {
            throw new IllegalStateException(
                    "Administrators cannot change their own role or status");
        }
    }

    private void ensureAnotherActiveAdministratorExists(User target) {
        long otherActiveAdministrators = userRepository.findByRoleAndStatusForUpdate(
                UserRole.ADMIN,
                UserStatus.ACTIVE)
                .stream()
                .filter(user -> !user.getUserId().equals(target.getUserId()))
                .count();

        if (otherActiveAdministrators == 0) {
            throw new IllegalStateException(
                    "The last active administrator cannot be demoted or deactivated");
        }
    }

    private boolean contains(String value, String search) {
        return value != null
                && value.toLowerCase(Locale.ROOT).contains(search);
    }
}
