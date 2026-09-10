package com.voxera.backend.user.service;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                        new IllegalArgumentException("User not found: " + userId));
    }

    @Transactional(readOnly = true)
    public User getUserByEmployeeId(String employeeId) {
        return userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found: " + employeeId));
    }
}
