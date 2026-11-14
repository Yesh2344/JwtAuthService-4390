package com.example.jwtauth.service;

import com.example.jwtauth.model.User;
import com.example.jwtauth.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling authentication logic.
 */
@Service
@Slf4j
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Authenticates a user against an in‑memory store.
     *
     * @param username Provided username.
     * @param password Provided password.
     * @return JWT if authentication succeeds.
     * @throws IllegalArgumentException when credentials are invalid.
     */
    public String authenticate(String username, String password) {
        // In a real application, replace this with a DB lookup + password hash verification.
        User dummyUser = findUserByUsername(username);
        if (dummyUser == null) {
            log.info("Authentication failed: unknown user '{}'", username);
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!dummyUser.getPassword().equals(password)) {
            log.info("Authentication failed: wrong password for user '{}'", username);
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(dummyUser.getUsername(), dummyUser.getRole());
        log.info("User '{}' authenticated successfully. JWT issued.", username);
        return token;
    }

    private User findUserByUsername(String username) {
        // Hard‑coded demo users.
        if ("john".equals(username)) {
            return new User(1L, "john", "password123", "ROLE_USER");
        } else if ("admin".equals(username)) {
            return new User(2L, "admin", "adminPass", "ROLE_ADMIN");
        }
        return null;
    }
}