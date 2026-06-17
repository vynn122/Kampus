package com.example.kampus.modules.verification.crypto;


import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class HmacOtpHashingService implements OtpHashingService{




    private String secretKey =  "sguyyqqweyyiwweryiuewyriuewerewgregwruhjfhj";
    private static final String HMAC_ALGORITHM = "HmacSHA256";



    @Override
    @SneakyThrows
    public String hash(String otp) {
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
        mac.init(keySpec);
        byte[] hashedByte  = mac.doFinal(otp.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedByte);
    }

    @Override
    public boolean matches(String rawOtp, String hashedOtp) {
        return hash(rawOtp).equals(hashedOtp);
    }
}
