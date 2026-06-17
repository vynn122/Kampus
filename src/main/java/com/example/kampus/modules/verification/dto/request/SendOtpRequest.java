package com.example.kampus.modules.verification.dto.request;

import com.example.kampus.modules.verification.enums.VerificationPurpose;
import com.example.kampus.modules.verification.validatiton.annotation.ValidIdentifier;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SendOtpRequest {


    @ValidIdentifier
    private String identifier;



    @NotNull
    private VerificationPurpose purpose;
}
