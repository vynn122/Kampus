package com.example.kampus.modules.verification.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class OtpGeneratorService {
    final SecureRandom random = new SecureRandom();

    public String generateOtpCode(){
        return String.valueOf(100000 + random.nextInt(900000));
    }
}
