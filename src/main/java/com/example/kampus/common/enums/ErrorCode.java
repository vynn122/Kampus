package com.example.kampus.common.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    /*
     * =========================================================
     * AUTHENTICATION
     * =========================================================
     */

    INVALID_CREDENTIALS(
            "AUTH_001",
            "Invalid credentials",
            HttpStatus.UNAUTHORIZED
    ),

    UNAUTHORIZED(
            "AUTH_002",
            "Unauthorized",
            HttpStatus.UNAUTHORIZED
    ),

    ACCESS_DENIED(
            "AUTH_003",
            "Access denied",
            HttpStatus.FORBIDDEN
    ),

    ACCOUNT_DISABLED(
            "AUTH_004",
            "Account disabled",
            HttpStatus.FORBIDDEN
    ),

    ACCOUNT_LOCKED(
            "AUTH_005",
            "Account locked",
            HttpStatus.FORBIDDEN
    ),

    ACCOUNT_NOT_VERIFIED(
            "AUTH_006",
            "Account not verified",
            HttpStatus.FORBIDDEN
    ),

    TOO_MANY_LOGIN_ATTEMPTS(
            "AUTH_007",
            "Too many login attempts",
            HttpStatus.TOO_MANY_REQUESTS
    ),

    /*
     * =========================================================
     * JWT
     * =========================================================
     */

    TOKEN_EXPIRED(
            "JWT_001",
            "Token expired",
            HttpStatus.UNAUTHORIZED
    ),

    TOKEN_INVALID(
            "JWT_002",
            "Invalid token",
            HttpStatus.UNAUTHORIZED
    ),

    TOKEN_MISSING(
            "JWT_003",
            "Token missing",
            HttpStatus.UNAUTHORIZED
    ),

    /*
     * =========================================================
     * USER
     * =========================================================
     */

    USER_NOT_FOUND(
            "USER_001",
            "User not found",
            HttpStatus.NOT_FOUND
    ),

    EMAIL_ALREADY_EXISTS(
            "USER_002",
            "Email already exists",
            HttpStatus.CONFLICT
    ),

    USERNAME_ALREADY_EXISTS(
            "USER_003",
            "Username already exists",
            HttpStatus.CONFLICT
    ),

    PHONE_ALREADY_EXISTS(
            "USER_004",
            "Phone already exists",
            HttpStatus.CONFLICT
    ),

    /*
     * =========================================================
     * OTP
     * =========================================================
     */

    OTP_INVALID(
            "OTP_001",
            "Invalid OTP",
            HttpStatus.BAD_REQUEST
    ),

    OTP_EXPIRED(
            "OTP_002",
            "OTP expired",
            HttpStatus.BAD_REQUEST
    ),

    OTP_LOCKED(
            "OTP_003",
            "Too many invalid attempts",
            HttpStatus.TOO_MANY_REQUESTS
    ),

    OTP_REQUEST_TOO_FREQUENT(
            "OTP_004",
            "Too many OTP requests",
            HttpStatus.TOO_MANY_REQUESTS
    ),

    OTP_COOLDOWN(
            "OTP_005",
            "Please wait before requesting another OTP",
            HttpStatus.TOO_MANY_REQUESTS
    ),


    /*
     * =========================================================
     * VALIDATION
     * =========================================================
     */

    VALIDATION_ERROR(
            "REQ_001",
            "Validation failed",
            HttpStatus.BAD_REQUEST
    ),

    INVALID_REQUEST(
            "REQ_002",
            "Invalid request",
            HttpStatus.BAD_REQUEST
    ),

    PASSWORD_TOO_WEAK(
            "REQ_003",
            "Password too weak",
            HttpStatus.BAD_REQUEST
    ),

    /*
     * =========================================================
     * DATABASE
     * =========================================================
     */

    DATABASE_ERROR(
            "DB_001",
            "Database error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    DATA_INTEGRITY_VIOLATION(
            "DB_002",
            "Data integrity violation",
            HttpStatus.CONFLICT
    ),

    /*
     * =========================================================
     * SERVER
     * =========================================================
     */

    INTERNAL_ERROR(
            "SYS_001",
            "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    SERVICE_UNAVAILABLE(
            "SYS_002",
            "Service unavailable",
            HttpStatus.SERVICE_UNAVAILABLE
    );

    private final String code;
    private final String message;
    private final HttpStatus status;
}
