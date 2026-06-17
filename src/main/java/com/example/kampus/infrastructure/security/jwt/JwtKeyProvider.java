package com.example.kampus.infrastructure.security.jwt;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Getter
@Component
@RequiredArgsConstructor
public class JwtKeyProvider {

    private final JwtProperties jwtProperties;
    private final ResourceLoader resourceLoader;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws Exception {
        System.out.println(jwtProperties.getPrivateKey()+ "hAHHAHAHAHHAHAH");
        System.out.println(jwtProperties.getPublicKey());

        this.privateKey = loadPrivateKey(
                jwtProperties.getPrivateKey()
        );

        this.publicKey = loadPublicKey(
                jwtProperties.getPublicKey()
        );
    }

    private String readKey(String path) throws IOException {
        return new String(
                resourceLoader
                        .getResource(path)
                        .getInputStream()
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
    }

    private PrivateKey loadPrivateKey(String path) throws Exception {

        String key = readKey(path);

        key = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(decoded);

        return KeyFactory.getInstance("RSA")
                .generatePrivate(spec);
    }

    private PublicKey loadPublicKey(String path) throws Exception {

        String key = readKey(path);

        key = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(decoded);

        return KeyFactory.getInstance("RSA")
                .generatePublic(spec);
    }
}