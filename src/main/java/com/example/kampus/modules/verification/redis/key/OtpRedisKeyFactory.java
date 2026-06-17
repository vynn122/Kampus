package com.example.kampus.modules.verification.redis.key;


import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.enums.VerificationPurpose;
import org.springframework.stereotype.Component;

@Component
public class OtpRedisKeyFactory {
    public String otpKey(
            VerificationPurpose purpose,
            VerificationChannel channel,
            String identifier
    ){

        return String.format(
                "otp:%s:%s:%s",
                purpose.name().toLowerCase(),
                channel.name().toLowerCase(),
                identifier
        );
    }

    public String cooldownKey(
            VerificationPurpose purpose,
            String identifier
    ){

        return String.format(
                "otp:cooldown:%s:%s",
                purpose.name().toLowerCase(),
                identifier
        );
    }

    public String rateLimitKey(
            VerificationPurpose purpose,
            String identifier
    ){

        return String.format(
                "otp:rate-limit:%s:%s",
                purpose.name().toLowerCase(),
                identifier
        );
    }
}
