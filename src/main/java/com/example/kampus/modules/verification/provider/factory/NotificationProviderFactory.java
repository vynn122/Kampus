package com.example.kampus.modules.verification.provider.factory;


import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.provider.NotificationProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationProviderFactory {
    private final List<NotificationProvider> providers;
    private final Map<VerificationChannel, NotificationProvider> registry  = new EnumMap<>(VerificationChannel.class);
    @PostConstruct
    public void init(){
        for(NotificationProvider provider : providers){
            registry.put(provider.supports(), provider);
        }
    }
    public NotificationProvider getProvider(VerificationChannel channel){
        return registry.get(channel);
    }
}
