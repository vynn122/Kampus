package com.example.kampus.modules.verification.policy.composite;


import com.example.kampus.modules.verification.enums.VerificationPurpose;
import com.example.kampus.modules.verification.policy.attempt.AttemptPolicy;
import com.example.kampus.modules.verification.policy.cooldown.CooldownPolicy;
import com.example.kampus.modules.verification.policy.expiration.ExpirationPolicy;
import com.example.kampus.modules.verification.policy.purpose.PurposeValidatorPolicy;
import com.example.kampus.modules.verification.policy.rate_limit.RateLimitPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VerificationPolicyManager {
    private final AttemptPolicy attemptPolicy;
    private final ExpirationPolicy expirationPolicy;
    private final PurposeValidatorPolicy purposeValidatorPolicy;
    private final CooldownPolicy cooldownPolicy;
    private final RateLimitPolicy rateLimitPolicy;


    public void validateSendOtp(LocalDateTime lastSentAt, int resendCount){
        cooldownPolicy.validate(lastSentAt);
        rateLimitPolicy.validate(resendCount);

    }
    public void validateVerifyOtp(int attempts, LocalDateTime expiresAt, VerificationPurpose expected, VerificationPurpose actual) {
        attemptPolicy.validate(attempts);
        expirationPolicy.validate(expiresAt);
        purposeValidatorPolicy.validate(expected, actual);

    }
}
