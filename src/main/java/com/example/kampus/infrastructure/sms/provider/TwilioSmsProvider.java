package com.example.kampus.infrastructure.sms.provider;


import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.provider.NotificationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class TwilioSmsProvider implements NotificationProvider {

    @Override
    public VerificationChannel supports() {
        return VerificationChannel.SMS;
    }

    @Override
    public void send(String recipient, String message) {
        log.info("Sending SMS to {} with message {}", recipient, message);

    }
}
