package com.example.kampus.modules.auth.dto.request;

import com.example.kampus.modules.auth.enums.AuthProviderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerifyOtpRequest {


    @NotNull
    private AuthProviderType authProviderType;
    @NotBlank
    private String target;
    @NotBlank(message = "OTP is required")
    private String otp;
}
