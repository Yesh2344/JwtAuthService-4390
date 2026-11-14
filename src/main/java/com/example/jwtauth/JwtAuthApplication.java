package com.example.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the JWT Authentication Service.
 */
@SpringBootApplication
public class JwtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApplication.class, args);
    }
}