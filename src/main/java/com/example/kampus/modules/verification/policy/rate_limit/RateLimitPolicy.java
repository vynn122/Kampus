package com.example.kampus.modules.verification.policy.rate_limit;

public interface RateLimitPolicy {


    void  validate(int resendCount);
}
