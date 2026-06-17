package com.example.kampus.modules.verification.provider;

import com.example.kampus.modules.verification.enums.VerificationChannel;

public interface NotificationProvider {
    VerificationChannel supports();
    void send(String recipient, String message);
}
