package com.example.kampus.modules.verification.template;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import org.springframework.stereotype.Component;


@Component
public class EmailOtpTemplate implements  OtpTemplate{
    @Override
    public VerificationChannel supportChannels() {
        return VerificationChannel.EMAIL;
    }

    @Override
    public String buildMessage(String otp) {
        return"""
                <div style="font-family: Arial, sans-serif;">
                    <h2>Kampus Verification Code</h2>
                    <p>Your OTP code is:</p>

                    <div style="
                        font-size: 32px;
                        font-weight: bold;
                        letter-spacing: 5px;
                        margin: 20px 0;
                    ">
                        %s
                    </div>

                    <p>This code expires in 5 minutes.</p>
                </div>
                """.formatted(otp);
    }
}
