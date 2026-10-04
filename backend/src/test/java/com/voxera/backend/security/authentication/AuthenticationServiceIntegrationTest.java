package com.voxera.backend.security.authentication;

import com.voxera.backend.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceIntegrationTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void authenticate_shouldAuthenticateSeededDevelopmentUser() {
        User user = authenticationService.authenticate(
                "emp001@voxera.local",
                "VoxeraDev123!"
        );

        assertNotNull(user);
        assertEquals("emp001@voxera.local", user.getEmail());
        assertEquals("EMP001", user.getEmployeeId());
        assertEquals("ACTIVE", user.getStatus());
    }

    @Test
    void authenticate_shouldRejectInvalidPassword() {
        assertThrows(
                BadCredentialsException.class,
                () -> authenticationService.authenticate(
                        "emp001@voxera.local",
                        "wrong-password"
                )
        );
    }
}
