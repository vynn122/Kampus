package com.example.kampus.modules.verification.controller;

import com.example.kampus.modules.auth.dto.request.VerifyOtpRequest;
import com.example.kampus.modules.verification.dto.request.SendOtpRequest;
import com.example.kampus.modules.verification.dto.response.VerificationResponse;
import com.example.kampus.modules.verification.service.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/verifications")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/send-otp")
    public VerificationResponse sendOtp(
            @Valid
            @RequestBody
            SendOtpRequest request
    ){

        verificationService.sendOtp(request);
//
//        verificationService.sendOtp(
//                request
//        );

        return VerificationResponse.builder()
//                .success(true)
                .message("OTP sent successfully")
                .build();
    }

    @PostMapping("/verify-otp")
    public VerificationResponse verifyOtp(
            @Valid
            @RequestBody
            VerifyOtpRequest request
    ){

        verificationService.verifyOtp(
                request
        );

        return VerificationResponse.builder()
                .message("OTP verified successfully")
                .build();
    }
}