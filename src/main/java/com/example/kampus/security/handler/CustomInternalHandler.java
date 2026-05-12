package com.example.kampus.security.handler;

import com.example.kampus.common.enums.ErrorCode;
import com.example.kampus.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomInternalHandler {
    private final ObjectMapper objectMapper;
    public void handle(HttpServletResponse response, Exception ex) throws IOException {
        log.error("Security internal exception", ex);
        ErrorCode error = ErrorCode.INTERNAL_ERROR;
        response.setStatus(error.getStatus().value());
        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ApiResponse.fail(
                                error.getCode(),
                                error.getMessage(),
                                null
                        )
                )
        );
    }
}
