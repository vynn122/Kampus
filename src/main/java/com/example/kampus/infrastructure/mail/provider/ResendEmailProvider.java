package com.example.kampus.infrastructure.mail.provider;


import com.example.kampus.infrastructure.mail.client.ResendClient;
import com.example.kampus.infrastructure.mail.dto.ResendEmailRequest;
import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.provider.NotificationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResendEmailProvider implements NotificationProvider {
    private final ResendClient resendClient;



    @Override
    public VerificationChannel supports() {
        return VerificationChannel.EMAIL;
    }

    @Override
    public void send(String recipient, String message) {
        log.info("Sending email to {} with message {}", recipient, message);
        ResendEmailRequest request =
                ResendEmailRequest.builder()
                        .from("Kampus <onboarding@resend.dev>")
                        //.from("OTP Service <verify@mail.kampus.app>")
                        .to(recipient)
                        .subject("Kampus Verification")
                        .html(message)
                        .build();

        resendClient.sendEmail(request);

    }
}
