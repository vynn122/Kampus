package com.example.kampus.modules.verification.template.factory;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.template.OtpTemplate;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;



@Component
@RequiredArgsConstructor
public class OtpTemplateFactory {
    private final List<OtpTemplate> templates;
    private final Map<VerificationChannel, OtpTemplate> registry = new EnumMap<>(VerificationChannel.class);

    @PostConstruct
    public void init(){
        for(OtpTemplate template : templates){
            registry.put(template.supportChannels(), template);
        }
    }
    public OtpTemplate getOtpTemplate(VerificationChannel channel){
        OtpTemplate template = registry.get(channel);
        if(template == null){
            throw new RuntimeException("Unsupported templates: " + channel);
        }
        return template;
    }
}
