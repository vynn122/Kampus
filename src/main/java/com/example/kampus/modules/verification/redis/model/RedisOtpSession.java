package com.example.kampus.modules.verification.redis.model;


import com.example.kampus.modules.verification.enums.VerificationPurpose;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisOtpSession {
    private String hashedOtp;
    private Integer attempts;
//    private Long expiresAt;

    private Integer resendCount;
    private LocalDateTime lastSentAt;

    private LocalDateTime expiresAt;

    private VerificationPurpose type;
}
