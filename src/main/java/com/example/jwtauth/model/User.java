package com.example.jwtauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple user domain object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password; // In production, store a BCrypt hash.
    private String role; // e.g., ROLE_USER, ROLE_ADMIN
}