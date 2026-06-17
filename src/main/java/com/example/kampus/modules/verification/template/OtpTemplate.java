package com.example.kampus.modules.verification.template;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import org.springframework.stereotype.Component;


public interface OtpTemplate {
    VerificationChannel supportChannels();
    String buildMessage(String otp);
}
