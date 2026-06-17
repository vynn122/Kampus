package com.example.kampus.modules.verification.policy.rate_limit;


import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import org.springframework.stereotype.Component;

@Component
public class DefaultRateLimitPolicy implements RateLimitPolicy {


    private static final int MAX_RESEND = 3;
    @Override
    public void validate(int resendCount) {
        if(resendCount >= MAX_RESEND) {
            throw new ApiException(ErrorCode.OTP_REQUEST_TOO_FREQUENT);
        }
    }
}
