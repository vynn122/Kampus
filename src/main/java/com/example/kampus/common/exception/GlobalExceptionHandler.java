package com.example.kampus.common.exception;


import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.response.ApiResponse;
import com.example.kampus.common.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * =========================================================
     * VALIDATION
     * =========================================================
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        field ->
                                                field.getField(),

                                        field ->
                                                field.getDefaultMessage(),

                                        (v1, v2) -> v1
                                )
                        );

        ErrorCode error =
                ErrorCode.VALIDATION_ERROR;

        return ResponseEntity
                .status(error.getStatus())
                .body(
                        new ValidationErrorResponse(
                                false,
                                error.getCode(),
                                error.getMessage(),
                                request.getRequestURI(),
                                errors,
                                System.currentTimeMillis()
                        )
                );
    }

    /*
     * =========================================================
     * CUSTOM API EXCEPTION
     * =========================================================
     */

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(
            ApiException ex,
            HttpServletRequest request
    ) {

        ErrorCode error =
                ex.getErrorCode();

        return ResponseEntity
                .status(error.getStatus())
                .body(
                        ApiResponse.fail(
                                error.getCode(),
                                error.getMessage(),
                                request.getRequestURI()
                        )
                );
    }

    /*
     * =========================================================
     * DATABASE
     * =========================================================
     */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDatabase(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {

        log.error("Database exception", ex);

        ErrorCode error =
                ErrorCode.DATABASE_ERROR;

        return ResponseEntity
                .status(error.getStatus())
                .body(
                        ApiResponse.fail(
                                error.getCode(),
                                error.getMessage(),
                                request.getRequestURI()
                        )
                );
    }

    /*
     * =========================================================
     * INTERNAL ERROR
     * =========================================================
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        log.error("Unhandled exception", ex);

        ErrorCode error =
                ErrorCode.INTERNAL_ERROR;

        return ResponseEntity
                .status(error.getStatus())
                .body(
                        ApiResponse.fail(
                                error.getCode(),
                                error.getMessage(),
                                request.getRequestURI()
                        )
                );
    }

}