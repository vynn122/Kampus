package com.example.kampus.modules.verification.strategy;


import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.enums.VerificationPurpose;
import com.example.kampus.modules.verification.provider.factory.NotificationProviderFactory;
import com.example.kampus.modules.verification.service.OtpGeneratorService;
import com.example.kampus.modules.verification.template.factory.OtpTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneVerificationStrategy implements VerificationStrategy {
    private final NotificationProviderFactory notificationProviderFactory;
    private final OtpGeneratorService otpGeneratorService;
    private final OtpTemplateFactory otpTemplateFactory;
    @Override
    public VerificationChannel getChannel() {
        return VerificationChannel.SMS;
    }

    @Override
    public void sendOtp(String identifier, VerificationPurpose purpose) {

    }

    @Override
    public boolean verifyOtp(String identifier, String otp, VerificationPurpose purpose) {
        return false;
    }

//    @Override
//    public void sendOtp(String identifier) {
//        String otp = otpGeneratorService.generateOtpCode();
//        String message = otpTemplateFactory.getOtpTemplate(VerificationChannel.SMS).buildMessage(otp);
//        notificationProviderFactory.getProvider(VerificationChannel.SMS).send(identifier, message);
//    }
}
