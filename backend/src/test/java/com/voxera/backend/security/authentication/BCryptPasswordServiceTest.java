package com.voxera.backend.security.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordServiceTest {

    private final PasswordService passwordService =
            new BCryptPasswordService();

    @Test
    void hash_shouldNotReturnRawPassword() {
        String rawPassword = "secret123";

        String hash = passwordService.hash(rawPassword);

        assertNotEquals(rawPassword, hash);
    }

    @Test
    void matches_shouldReturnTrueForCorrectPassword() {
        String rawPassword = "secret123";
        String hash = passwordService.hash(rawPassword);

        assertTrue(passwordService.matches(rawPassword, hash));
    }

    @Test
    void matches_shouldReturnFalseForIncorrectPassword() {
        String hash = passwordService.hash("secret123");

        assertFalse(passwordService.matches("wrongPassword", hash));
    }

    @Test
    void hash_shouldProduceDifferentHashesForSamePassword() {
        String firstHash = passwordService.hash("secret123");
        String secondHash = passwordService.hash("secret123");

        assertNotEquals(firstHash, secondHash);

        assertTrue(passwordService.matches("secret123", firstHash));
        assertTrue(passwordService.matches("secret123", secondHash));
    }
}
