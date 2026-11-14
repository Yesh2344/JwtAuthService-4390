package com.example.jwtauth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for generating and validating JWT tokens.
 */
@Slf4j
public class JwtUtil {

    private final Key signingKey;
    private final long jwtExpirationMs;

    /**
     * Creates a JwtUtil instance.
     *
     * @param secret          Base64‑encoded secret key.
     * @param jwtExpirationMs Expiration time in milliseconds.
     */
    public JwtUtil(String secret, long jwtExpirationMs) {
        this.signingKey = Keys.hmacShaKeyFor(io.jsonwebtoken.io.Decoders.BASE64.decode(secret));
        this.jwtExpirationMs = jwtExpirationMs;
    }

    /**
     * Generates a JWT for a given username and role.
     *
     * @param username Username to embed.
     * @param role     Role to embed.
     * @return Signed JWT as a String.
     */
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts the username (subject) from a token.
     *
     * @param token JWT.
     * @return Username.
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Validates a JWT.
     *
     * @param token JWT.
     * @return true if valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
// small cleanup
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}