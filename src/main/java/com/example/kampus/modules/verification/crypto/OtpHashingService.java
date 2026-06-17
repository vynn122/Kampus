package com.example.kampus.modules.verification.crypto;

public interface OtpHashingService {
    String hash(String otp);
    boolean matches(String rawOtp, String hashedOtp);
}
