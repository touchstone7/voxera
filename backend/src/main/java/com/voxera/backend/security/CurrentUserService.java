package com.voxera.backend.security;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()) {

            throw new AuthenticationCredentialsNotFoundException(
                    "No authenticated user");
        }

        UUID userId;

        try {
            userId = UUID.fromString(
                    authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Invalid authenticated user");
        }

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new AuthenticationCredentialsNotFoundException(
                                "Authenticated user not found"));
    }
}