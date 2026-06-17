package com.example.kampus.infrastructure.cache.client;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisClientFactory {


    private final RedisTemplate<String, Object> redisTemplate;
    @Bean
    public RedisTemplate<String,Object> getClientRedisTemplate(){
        return redisTemplate;
    }
}
