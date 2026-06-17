package com.example.kampus.modules.verification.policy.cooldown;


import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultCooldownPolicy implements CooldownPolicy{

    private static final int COOLDOWN_SECONDS = 30;
    @Override
    public void validate(LocalDateTime lastSentAt) {
        if (lastSentAt == null) {
            return;
        }
        if(lastSentAt.plusSeconds(COOLDOWN_SECONDS).isAfter(LocalDateTime.now())){
            throw new ApiException(ErrorCode.OTP_COOLDOWN);
        }
    }
}
