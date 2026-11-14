package com.example.jwtauth.service;

import com.example.jwtauth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        // Use a deterministic secret for testing.
        String secret = "dGVzdF9zZWNyZXRfdG9rZW5fMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM="; // base64 of "test_secret_token_12345678901234567890123"
        JwtUtil jwtUtil = new JwtUtil(secret, 3600000);
        authService = new AuthService(jwtUtil);
    }

    @Test
    void authenticate_ValidUser_ReturnsToken() {
        String token = authService.authenticate("john", "password123");
        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3); // simple JWT structure check
    }

    @Test
    void authenticate_InvalidPassword_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> authService.authenticate("john", "wrongPassword"));
        assertEquals("Invalid credentials", ex.getMessage());
    }

    @Test
    void authenticate_UnknownUser_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> authService.authenticate("unknown", "any"));
        assertEquals("Invalid credentials", ex.getMessage());
    }
}