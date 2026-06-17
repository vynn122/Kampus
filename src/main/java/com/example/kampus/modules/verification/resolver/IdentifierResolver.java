package com.example.kampus.modules.verification.resolver;


import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.service.VerificationService;
import com.example.kampus.modules.verification.validatiton.util.EmailValidatorUtil;
import com.example.kampus.modules.verification.validatiton.util.PhoneValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdentifierResolver {
//    public VerificationChannel resolve(String identifier){
//        if(identifier.contains("@")){
//            log.info("Email");
//            return VerificationChannel.EMAIL;
//        }
//
//        log.info("SMS");
//        return VerificationChannel.SMS;
//    }

    public VerificationChannel resolve(
            String identifier
    ){

        VerificationChannel channel =
                identifier.contains("@")
                        ? VerificationChannel.EMAIL
                        : VerificationChannel.SMS;

        log.debug(
                "Resolved channel {} for identifier {}",
                channel,
                identifier
        );

        return channel;
    }

}
