package com.example.kampus.modules.auth.dto.request;

import com.example.kampus.modules.auth.enums.AuthProviderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SentOtpRequest {


    @NotNull
    private AuthProviderType authProviderType;
    @NotBlank
    private String target;
}
