package com.voxera.backend.security.authentication;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.entity.UserCredential;
import com.voxera.backend.user.repository.UserCredentialRepository;
import com.voxera.backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @Mock
    private PasswordService passwordService;

    private final UUID userId =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    private User createUser(String status) {
        LocalDateTime now = LocalDateTime.now();

        return new User(
                userId,
                "EMP001",
                "Ranjan Kumar",
                "emp001@voxera.local",
                "IT",
                "EMPLOYEE",
                status,
                now,
                now);
    }

    private UserCredential createCredential() {
        LocalDateTime now = LocalDateTime.now();

        return new UserCredential(
                userId,
                "hashed-password",
                now,
                now);
    }

    @Test
    void authenticate_shouldReturnUserForValidCredentials() {
        User user = createUser("ACTIVE");
        UserCredential credential = createCredential();

        when(userRepository.findByEmail("emp001@voxera.local"))
                .thenReturn(Optional.of(user));
        when(userCredentialRepository.findById(userId))
                .thenReturn(Optional.of(credential));
        when(passwordService.matches("secret123", "hashed-password"))
                .thenReturn(true);

        User authenticatedUser =
                new AuthenticationService(
                        userRepository,
                        userCredentialRepository,
                        passwordService)
                        .authenticate("emp001@voxera.local", "secret123");

        assertEquals(userId, authenticatedUser.getUserId());
        verify(passwordService).matches("secret123", "hashed-password");
    }

    @Test
    void authenticate_shouldRejectUnknownEmail() {
        when(userRepository.findByEmail("unknown@voxera.local"))
                .thenReturn(Optional.empty());

        AuthenticationService service =
                new AuthenticationService(
                        userRepository,
                        userCredentialRepository,
                        passwordService);

        assertThrows(
                BadCredentialsException.class,
                () -> service.authenticate(
                        "unknown@voxera.local",
                        "secret123"));

        verifyNoInteractions(userCredentialRepository);
        verifyNoInteractions(passwordService);
    }

    @Test
    void authenticate_shouldRejectWrongPassword() {
        User user = createUser("ACTIVE");
        UserCredential credential = createCredential();

        when(userRepository.findByEmail("emp001@voxera.local"))
                .thenReturn(Optional.of(user));
        when(userCredentialRepository.findById(userId))
                .thenReturn(Optional.of(credential));
        when(passwordService.matches("wrong-password", "hashed-password"))
                .thenReturn(false);

        AuthenticationService service =
                new AuthenticationService(
                        userRepository,
                        userCredentialRepository,
                        passwordService);

        assertThrows(
                BadCredentialsException.class,
                () -> service.authenticate(
                        "emp001@voxera.local",
                        "wrong-password"));
    }

    @Test
    void authenticate_shouldRejectInactiveUser() {
        User user = createUser("INACTIVE");

        when(userRepository.findByEmail("emp001@voxera.local"))
                .thenReturn(Optional.of(user));

        AuthenticationService service =
                new AuthenticationService(
                        userRepository,
                        userCredentialRepository,
                        passwordService);

        assertThrows(
                BadCredentialsException.class,
                () -> service.authenticate(
                        "emp001@voxera.local",
                        "secret123"));

        verifyNoInteractions(userCredentialRepository);
        verifyNoInteractions(passwordService);
    }

    @Test
    void authenticate_shouldRejectMissingCredential() {
        User user = createUser("ACTIVE");

        when(userRepository.findByEmail("emp001@voxera.local"))
                .thenReturn(Optional.of(user));
        when(userCredentialRepository.findById(userId))
                .thenReturn(Optional.empty());

        AuthenticationService service =
                new AuthenticationService(
                        userRepository,
                        userCredentialRepository,
                        passwordService);

        assertThrows(
                BadCredentialsException.class,
                () -> service.authenticate(
                        "emp001@voxera.local",
                        "secret123"));

        verifyNoInteractions(passwordService);
    }
}
