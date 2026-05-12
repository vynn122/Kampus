package com.example.kampus.common.response;

public record ApiResponse<T>
        (boolean success, String code, String message, String path, T data, Long timestamp
        ) {
    public static <T> ApiResponse<T> success(String message,T data , String path){
        return new ApiResponse<>(
                true,
                null,
                message,
                path,
                data,
                System.currentTimeMillis());
    }
    public static <T> ApiResponse<T> fail(String code,String message, String path){
        return new ApiResponse<>(
                false,
                code,
                message,
                path,
                null,
                System.currentTimeMillis()
        );
    }




}
