package com.voxera.backend.security.authentication;

import com.voxera.backend.security.authentication.dto.LoginRequest;
import com.voxera.backend.security.authentication.dto.LoginResponse;
import com.voxera.backend.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = authenticationService.authenticate(
                request.email(),
                request.password()
        );

        LoginResponse response = new LoginResponse(
                user.getUserId(),
                user.getEmployeeId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
