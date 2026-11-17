package com.voxera.backend.user.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;

import java.util.List;
import java.util.Locale;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    private boolean contains(String value, String search) {
        return value != null
                && value.toLowerCase(Locale.ROOT).contains(search);
    }
}
