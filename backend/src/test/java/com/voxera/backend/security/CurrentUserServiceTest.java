package com.voxera.backend.security;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CurrentUserServiceTest {

    private final UserRepository userRepository =
            mock(UserRepository.class);

    private final CurrentUserService currentUserService =
            new CurrentUserService(userRepository);

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getCurrentUser_shouldReturnAuthenticatedUser() {

        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                userId,
                "TEST-001",
                "Test User",
                "test@example.com",
                "IT",
                "EMPLOYEE",
                "ACTIVE",
                now,
                now
        );

        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(
                    userId.toString(),
                    null,
                    java.util.List.of()
            )
        );

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        User result = currentUserService.getCurrentUser();

        assertEquals(userId, result.getUserId());
    }

    @Test
    void getCurrentUser_shouldRejectUnauthenticatedRequest() {

        assertThrows(
                Exception.class,
                () -> currentUserService.getCurrentUser()
        );
    }

    @Test
    void getCurrentUser_shouldRejectUnknownUser() {

        UUID userId = UUID.randomUUID();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userId.toString(),
                        null
                )
        );

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        assertThrows(
                Exception.class,
                () -> currentUserService.getCurrentUser()
        );
    }
}