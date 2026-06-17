package com.example.kampus.modules.verification.strategy.factory;

import com.example.kampus.modules.verification.enums.VerificationChannel;
import com.example.kampus.modules.verification.strategy.VerificationStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VerificationStrategyFactory {
    private final List<VerificationStrategy>
            strategies;

    private final Map<
                VerificationChannel,
                VerificationStrategy
                > registry =
            new EnumMap<>(VerificationChannel.class);

    @PostConstruct
    public void init() {

        for (VerificationStrategy strategy : strategies) {

            registry.put(
                    strategy.getChannel(),
                    strategy
            );
        }
    }

    public VerificationStrategy getStrategy(
            VerificationChannel channel
    ) {

        VerificationStrategy strategy =
                registry.get(channel);

        if (strategy == null) {

            throw new RuntimeException(
                    "Unsupported strategy: " + channel
            );
        }

        return strategy;
    }
}
