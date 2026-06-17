package com.example.kampus.infrastructure.sms.provider;

public interface SmsOtpProvider {
    void send(
            String phone
    );
}
