package com.example.kampus.modules.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email
    @NotBlank(message = "Email is required.")
    private String email;

    private String phoneNumber;
    @NotBlank(message = "Password is required")
    private String password;
}
