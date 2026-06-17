package com.example.kampus.modules.verification.strategy.abstracts;

import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.exception.ApiException;
import com.example.kampus.modules.verification.crypto.OtpHashingService;
import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.enums.VerificationPurpose;
import com.example.kampus.modules.verification.policy.composite.VerificationPolicyManager;
import com.example.kampus.modules.verification.provider.factory.NotificationProviderFactory;
import com.example.kampus.modules.verification.redis.key.OtpRedisKeyFactory;
import com.example.kampus.modules.verification.redis.model.RedisOtpSession;
import com.example.kampus.modules.verification.redis.service.RedisOtpStore;
import com.example.kampus.modules.verification.service.OtpGeneratorService;
import com.example.kampus.modules.verification.strategy.VerificationStrategy;
import com.example.kampus.modules.verification.template.factory.OtpTemplateFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public abstract class AbstractVerificationStrategy implements VerificationStrategy {

    protected final OtpGeneratorService otpGeneratorService;
    protected final RedisOtpStore redisOtpStore;
    protected final OtpRedisKeyFactory keyFactory;
    protected final OtpTemplateFactory templateFactory;
    protected final NotificationProviderFactory providerFactory;
    protected final VerificationPolicyManager policyManager;
    protected final OtpHashingService otpHashingService;
    @Override
    public void sendOtp(String identifier, VerificationPurpose purpose) {

        String key = buildOtpKey(identifier, purpose);
        RedisOtpSession existingOtpSession = redisOtpStore.get(key);

        validateSendPolicies(existingOtpSession);
        String otp = otpGeneratorService.generateOtpCode();
        RedisOtpSession session = createSession(otp, purpose,existingOtpSession);
        redisOtpStore.save(key, session);
        sendNotification(identifier, otp);
    }

    @Override
    public boolean verifyOtp(String identifier, String otp, VerificationPurpose purpose) {
        String key = buildOtpKey(identifier, purpose);
        RedisOtpSession existingOtpSession = redisOtpStore.get(key);
        if (existingOtpSession == null) {
            throw new ApiException(ErrorCode.OTP_INVALID);
        }
        validateVerifyPolicies(existingOtpSession, purpose);
        boolean matched = otpHashingService.matches(otp, existingOtpSession.getHashedOtp());
        if (!matched) {
            existingOtpSession.setAttempts(existingOtpSession.getAttempts() + 1);
            redisOtpStore.save(key, existingOtpSession);
            return false;
        }
        redisOtpStore.delete(key);
        return true;
    }



    /// for build redis key
    private String buildOtpKey(String identifier, VerificationPurpose purpose) {
        return keyFactory.otpKey(purpose,getChannel(), identifier);
    }
    private void validateSendPolicies(RedisOtpSession session){
        if(session == null){
            return;
        }
        policyManager.validateSendOtp(
                session.getLastSentAt(),
                session.getResendCount()
        );
    }
    private void validateVerifyPolicies(RedisOtpSession session, VerificationPurpose purpose){
        if(session == null){
            return;
        }
        policyManager.validateVerifyOtp(
                session.getAttempts(),
                session.getExpiresAt(),
                purpose,
                session.getType()
        );

    }
    private RedisOtpSession createSession(String otp, VerificationPurpose purpose, RedisOtpSession existingSession) {
        return RedisOtpSession.builder()
                .hashedOtp(otpHashingService.hash(otp))
                .attempts(0)
                .resendCount(
                        existingSession == null
                                ? 0
                                : existingSession.getResendCount() + 1
                )
                .type(purpose)
                .lastSentAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();
    }
    private void sendNotification(String identifier, String otp) {
        String message = templateFactory.getOtpTemplate(getChannel()).buildMessage(otp);
        providerFactory.getProvider(getChannel()).send(identifier, message);
    }

}
