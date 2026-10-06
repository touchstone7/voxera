package com.voxera.backend.security.jwt;

import com.voxera.backend.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private static final String TEST_SECRET =
            "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";

    private JwtService jwtService;
    private User user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(TEST_SECRET, 900000);

        LocalDateTime now = LocalDateTime.now();

        user = new User(
                UUID.randomUUID(),
                "EMP001",
                "Test User",
                "test@example.com",
                "IT",
                "EMPLOYEE",
                "ACTIVE",
                now,
                now
        );
    }

    @Test
    void generateToken_shouldContainExpectedClaims() {
        String token = jwtService.generateToken(user);

        Claims claims = jwtService.extractClaims(token);

        assertEquals(user.getUserId().toString(), claims.getSubject());
        assertEquals(user.getEmployeeId(), claims.get("employeeId"));
        assertEquals(user.getRole(), claims.get("role"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    @Test
    void isTokenValid_shouldReturnTrueForCorrectUser() {
        String token = jwtService.generateToken(user);

        assertTrue(jwtService.isTokenValid(token, user));
    }

    @Test
    void isTokenValid_shouldReturnFalseForDifferentUser() {
        String token = jwtService.generateToken(user);

        LocalDateTime now = LocalDateTime.now();

        User differentUser = new User(
                UUID.randomUUID(),
                "EMP002",
                "Another Test User",
                "another@example.com",
                "IT",
                "EMPLOYEE",
                "ACTIVE",
                now,
                now
        );

        assertFalse(jwtService.isTokenValid(token, differentUser));
    }

    @Test
    void extractClaims_shouldRejectMalformedToken() {
        assertThrows(
                Exception.class,
                () -> jwtService.extractClaims("not-a-valid-jwt")
        );
    }

    @Test
    void extractClaims_shouldRejectTokenSignedWithDifferentKey() {
        byte[] differentKeyBytes =
                "different-test-key-with-at-least-32-bytes"
                        .getBytes(StandardCharsets.UTF_8);

        SecretKey differentKey =
                Keys.hmacShaKeyFor(differentKeyBytes);

        String forgedToken = Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("employeeId", user.getEmployeeId())
                .claim("role", user.getRole())
                .signWith(differentKey)
                .compact();

        assertThrows(
                Exception.class,
                () -> jwtService.extractClaims(forgedToken)
        );
    }
}