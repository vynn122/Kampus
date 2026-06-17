package com.example.kampus.modules.verification.policy.cooldown;

import java.time.LocalDateTime;

public interface CooldownPolicy {
    void validate(LocalDateTime lastSentAt);
}
