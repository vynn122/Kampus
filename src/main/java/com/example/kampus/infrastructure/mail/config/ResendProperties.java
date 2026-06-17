package com.example.kampus.infrastructure.mail.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ResendProperties {
    private String apiKey;
    private String fromEmail;
}
