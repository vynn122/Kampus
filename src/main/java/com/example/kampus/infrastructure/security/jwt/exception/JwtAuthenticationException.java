package com.example.kampus.infrastructure.security.jwt.exception;

public class JwtAuthenticationException
        extends RuntimeException {

    public JwtAuthenticationException(String message) {
        super(message);
    }
}