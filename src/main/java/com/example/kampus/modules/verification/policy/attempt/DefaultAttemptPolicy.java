package com.example.kampus.modules.verification.policy.attempt;


import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import org.springframework.stereotype.Component;

@Component
public class DefaultAttemptPolicy implements AttemptPolicy{
    private static final int MAX_ATTEMPTS= 5;

    @Override
    public void validate(int attempt) {
        if(attempt >= MAX_ATTEMPTS){
            throw new ApiException(ErrorCode.OTP_LOCKED);
        }
    }
}
