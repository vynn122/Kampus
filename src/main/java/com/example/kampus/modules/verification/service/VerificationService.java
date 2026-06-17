package com.example.kampus.modules.verification.service;

import com.example.kampus.modules.auth.dto.request.VerifyOtpRequest;
import com.example.kampus.modules.verification.crypto.OtpHashingService;
import com.example.kampus.modules.verification.dto.request.SendOtpRequest;
import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.enums.VerificationPurpose;
import com.example.kampus.modules.verification.provider.factory.NotificationProviderFactory;
import com.example.kampus.modules.verification.redis.key.OtpRedisKeyFactory;
import com.example.kampus.modules.verification.redis.model.RedisOtpSession;
import com.example.kampus.modules.verification.redis.service.RedisOtpStore;
import com.example.kampus.modules.verification.resolver.IdentifierResolver;
import com.example.kampus.modules.verification.strategy.VerificationStrategy;
import com.example.kampus.modules.verification.strategy.factory.VerificationStrategyFactory;
import com.example.kampus.modules.verification.template.factory.OtpTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class VerificationService {
    private final IdentifierResolver identifierResolver;

    private final VerificationStrategyFactory strategyFactory;

    public void sendOtp(
            SendOtpRequest request
    ){

        VerificationChannel channel =
                identifierResolver.resolve(
                        request.getIdentifier()
                );

        VerificationStrategy strategy =
                strategyFactory.getStrategy(channel);

        strategy.sendOtp(
                request.getIdentifier(),
                request.getPurpose()
        );
    }

    public boolean verifyOtp(
            VerifyOtpRequest request
    ){

        VerificationChannel channel =
                identifierResolver.resolve(
                        request.getIdentifier()
                );

        VerificationStrategy strategy =
                strategyFactory.getStrategy(channel);

        return strategy.verifyOtp(
                request.getIdentifier(),
                request.getOtp(),
                request.getPurpose()
        );
    }

}
