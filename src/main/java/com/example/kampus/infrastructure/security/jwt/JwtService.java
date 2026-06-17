package com.example.kampus.infrastructure.security.jwt;


import com.example.kampus.infrastructure.security.jwt.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtKeyProvider keyProvider;
    private final JwtProperties properties;

    public String generateAccessToken(UUID userId) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("type", "access");

        return buildToken(
                claims,
                userId,
                properties.getAccessTokenExpiration()
        );
    }

    public String generateRefreshToken(UUID userId) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("type", "refresh");

        return buildToken(
                claims,
                userId,
                properties.getRefreshTokenExpiration()
        );
    }

    private String buildToken(
            Map<String, Object> claims,
            UUID userId,
            long expiration
    ) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )
                .signWith(
                        keyProvider.getPrivateKey(),
                        SignatureAlgorithm.RS256
                )
                .compact();
    }

    public Claims extractClaims(String token) {

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(
                            (RSAPublicKey) keyProvider.getPublicKey()
                    )
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {

            throw new JwtAuthenticationException(
                    "Invalid or expired JWT token"
            );
        }
    }

    public UUID extractUserId(String token) {

        return UUID.fromString(
                extractClaims(token).getSubject()
        );
    }

    public String extractTokenType(String token) {

        return extractClaims(token)
                .get("type", String.class);
    }

    public boolean isValid(String token) {

        try {

            extractClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
