package com.example.kampus.common.response;

import java.util.Map;

public record ValidationErrorResponse(
        boolean success,

        String code,

        String message,

        String path,

        Map<String, String> errors,

        Long timestamp
) {
}
