package com.example.kampus.modules.verification.strategy;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.enums.VerificationPurpose;

public interface VerificationStrategy {

    VerificationChannel getChannel();


    void sendOtp(
            String identifier,
            VerificationPurpose purpose
    );

    boolean verifyOtp(
            String identifier,
            String otp,
            VerificationPurpose purpose
    );



//    void sendOtp(String identifier);




}
