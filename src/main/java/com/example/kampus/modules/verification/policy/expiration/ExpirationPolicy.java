package com.example.kampus.modules.verification.policy.expiration;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public interface ExpirationPolicy {
    void validate(LocalDateTime expiresAt);
}
