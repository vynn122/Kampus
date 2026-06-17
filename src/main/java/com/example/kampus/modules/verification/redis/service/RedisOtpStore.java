package com.example.kampus.modules.verification.redis.service;


import com.example.kampus.infrastructure.cache.client.RedisClientFactory;
import com.example.kampus.modules.verification.redis.model.RedisOtpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisOtpStore {
    private final RedisClientFactory redisClientFactory;
    private final ObjectMapper objectMapper;

    public void save(String key, RedisOtpSession session){
        try {
            String value = objectMapper.writeValueAsString(session);
            RedisTemplate<String, Object> redis = redisClientFactory.getClientRedisTemplate();
            redis.opsForValue().set(key,value, Duration.ofMinutes(5));

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public RedisOtpSession get(String key){
        try{
            Object value = redisClientFactory.getClientRedisTemplate().opsForValue().get(key);
            if(value == null){
                return null;
            }
            return objectMapper.readValue(value.toString(), RedisOtpSession.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    public void delete(String key){
        redisClientFactory.getClientRedisTemplate().delete(key);
    }


}
