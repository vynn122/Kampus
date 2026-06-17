package com.example.kampus.modules.verification.validatiton.util;


import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidatorUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            );

    public boolean isValid(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return EMAIL_PATTERN
                .matcher(email.trim())
                .matches();
    }
}
