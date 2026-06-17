package com.example.kampus.modules.verification.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
public class VerificationRequest {


    @NotBlank
    private String identifier;
    @NotBlank
    private String otp;
}
