package com.example.jwtauth.controller;

import com.example.jwtauth.service.AuthService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;

/**
 * REST controller exposing authentication endpoints.
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login endpoint. Returns a JWT on success.
     *
     * @param request JSON payload containing username and password.
     * @return JWT wrapped in a response body.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        log.debug("Login attempt for user '{}'", request.getUsername());
        String token = authService.authenticate(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @Data
    private static class LoginRequest {
        @NotBlank(message = "Username must not be blank")
        private String username;

        @NotBlank(message = "Password must not be blank")
        private String password;
    }

    @Data
    private static class LoginResponse {
        private final String token;
    }
}