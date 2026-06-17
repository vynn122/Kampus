package com.example.kampus.modules.verification.template;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import org.springframework.stereotype.Component;


@Component
public class SmsTemplate implements OtpTemplate {

    @Override
    public VerificationChannel supportChannels() {
        return VerificationChannel.SMS;
    }

    @Override
    public String buildMessage(String otp) {
        return "Your OTP i: " + otp;
    }
}
