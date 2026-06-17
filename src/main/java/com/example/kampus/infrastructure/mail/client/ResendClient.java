package com.example.kampus.infrastructure.mail.client;


import com.example.kampus.infrastructure.mail.config.ResendProperties;
import com.example.kampus.infrastructure.mail.dto.ResendEmailRequest;
import com.resend.Resend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ResendClient {
    @Value("${resend.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    private static final String RESEND_URL =
            "https://api.resend.com/emails";

    public void sendEmail(
            ResendEmailRequest request
    ){

        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(apiKey);

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<?> entity =
                new HttpEntity<>(request, headers);

        restTemplate.exchange(
                RESEND_URL,
                HttpMethod.POST,
                entity,
                String.class
        );
    }

}
