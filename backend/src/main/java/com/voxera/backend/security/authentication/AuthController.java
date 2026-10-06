package com.voxera.backend.security.authentication;

import com.voxera.backend.security.authentication.dto.LoginRequest;
import com.voxera.backend.security.authentication.dto.LoginResponse;
import com.voxera.backend.security.jwt.JwtService;
import com.voxera.backend.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationService authenticationService,
            JwtService jwtService) {

        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = authenticationService.authenticate(
                request.email(),
                request.password()
        );

        String accessToken = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse(
                user.getUserId(),
                user.getEmployeeId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                accessToken
        );

        return ResponseEntity.ok(response);
    }
}