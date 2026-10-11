package com.voxera.backend.security.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.entity.UserCredential;
import com.voxera.backend.user.enums.UserStatus;
import com.voxera.backend.user.repository.UserCredentialRepository;
import com.voxera.backend.user.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordService passwordService;

    public AuthenticationService(
            UserRepository userRepository,
            UserCredentialRepository userCredentialRepository,
            PasswordService passwordService) {
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.passwordService = passwordService;
    }

    public User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid credentials"));

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new BadCredentialsException("Invalid credentials");
        }

        UserCredential credential = userCredentialRepository.findById(user.getUserId())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid credentials"));

        if (!passwordService.matches(rawPassword, credential.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return user;
    }
}
