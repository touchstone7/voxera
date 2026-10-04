package com.voxera.backend.security.authentication;

public interface PasswordService {

    String hash(String rawPassword);

    boolean matches(String rawPassword, String passwordHash);
}
