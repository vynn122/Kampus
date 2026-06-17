package com.example.kampus.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(

        boolean success,
        String code,
        String message,
        String path,
        T data,
        OffsetDateTime timestamp

) {

    private static final ZoneId ZONE_ID =
            ZoneId.of("Asia/Phnom_Penh");

    public static <T> ApiResponse<T> success(
            String message,
            T data,
            String path
    ){

        return new ApiResponse<>(
                true,
                null,
                message,
                path,
                data,
                OffsetDateTime.now(ZONE_ID)
        );
    }

    public static <T> ApiResponse<T> fail(
            String code,
            String message,
            String path
    ){

        return new ApiResponse<>(
                false,
                code,
                message,
                path,
                null,
                OffsetDateTime.now(ZONE_ID)
        );
    }
}