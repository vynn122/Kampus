package com.example.kampus.modules.verification.strategy;

import com.example.kampus.modules.verification.crypto.OtpHashingService;
import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.policy.composite.VerificationPolicyManager;
import com.example.kampus.modules.verification.provider.factory.NotificationProviderFactory;
import com.example.kampus.modules.verification.redis.key.OtpRedisKeyFactory;
import com.example.kampus.modules.verification.redis.service.RedisOtpStore;
import com.example.kampus.modules.verification.service.OtpGeneratorService;
import com.example.kampus.modules.verification.strategy.abstracts.AbstractVerificationStrategy;
import com.example.kampus.modules.verification.template.factory.OtpTemplateFactory;
import org.springframework.stereotype.Service;


@Service
public class EmailVerificationStrategy extends AbstractVerificationStrategy {


    public EmailVerificationStrategy(OtpGeneratorService otpGeneratorService, RedisOtpStore redisOtpStore, OtpRedisKeyFactory keyFactory, OtpTemplateFactory templateFactory, NotificationProviderFactory providerFactory, VerificationPolicyManager policyManager, OtpHashingService otpHashingService) {
        super(otpGeneratorService, redisOtpStore, keyFactory, templateFactory, providerFactory, policyManager, otpHashingService);
    }

    @Override
    public VerificationChannel getChannel() {
        return VerificationChannel.EMAIL;
    }
}
