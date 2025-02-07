package com.example.auth.auth;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.auth.user.User;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String JWT_SECRET;

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("username", user.getUsername())
                .withExpiresAt(genAccessExpirationDate())
                .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new JWTCreationException("Error while creating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
        } catch(JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    // Tokens have a lifetime of 2 hours
    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(null);
    }
}
