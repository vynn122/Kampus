package com.example.kampus.modules.verification.policy.purpose;

import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import com.example.kampus.modules.verification.enums.VerificationPurpose;
import org.springframework.stereotype.Component;

@Component
public class DefaultPurposeValidatorPolicy implements PurposeValidatorPolicy {
    @Override
    public void validate(VerificationPurpose expected, VerificationPurpose actual) {
        if(expected != actual) {

            throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }

    }
}
