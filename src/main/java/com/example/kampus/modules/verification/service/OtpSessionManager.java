package com.example.kampus.modules.verification.service;


import com.example.kampus.modules.verification.redis.service.RedisOtpStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpSessionManager {
    private final RedisOtpStore redisOtpStore;
}
