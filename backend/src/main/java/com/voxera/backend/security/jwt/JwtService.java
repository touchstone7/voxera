package com.voxera.backend.security.jwt;

import com.voxera.backend.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationMs;

    public JwtService(
            @Value("${voxera.security.jwt.secret}") String secret,
            @Value("${voxera.security.jwt.expiration-ms}") long expirationMs) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationMs = expirationMs;

        if (expirationMs <= 0) {
            throw new IllegalArgumentException(
                    "JWT expiration must be positive");
        }
    }

    public String generateToken(User user) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(
                issuedAt.getTime() + expirationMs);

        return Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("employeeId", user.getEmployeeId())
                .claim("role", user.getRole())
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .signWith(signingKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public UUID extractUserId(String token) {
        return UUID.fromString(extractClaims(token).getSubject());
    }

    public boolean isTokenValid(String token, User user) {
        try {
            Claims claims = extractClaims(token);

            return claims.getSubject().equals(
                    user.getUserId().toString())
                    && claims.getExpiration().after(new Date());
        } catch (io.jsonwebtoken.JwtException
                 | IllegalArgumentException exception) {
            return false;
        }
    }
}
