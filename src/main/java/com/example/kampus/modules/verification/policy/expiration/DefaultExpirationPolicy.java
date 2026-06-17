package com.example.kampus.modules.verification.policy.expiration;


import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultExpirationPolicy implements ExpirationPolicy{
    @Override
    public void validate(LocalDateTime expiresAt) {

        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new ApiException(ErrorCode.OTP_EXPIRED);
        }

    }
}
