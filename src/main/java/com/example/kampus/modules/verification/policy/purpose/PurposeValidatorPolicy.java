package com.example.kampus.modules.verification.policy.purpose;

import com.example.kampus.modules.verification.enums.VerificationPurpose;

public interface PurposeValidatorPolicy {

    void validate(VerificationPurpose expected, VerificationPurpose actual);
}
