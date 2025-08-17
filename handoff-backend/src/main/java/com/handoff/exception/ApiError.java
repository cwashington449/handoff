package com.handoff.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private Instant timestamp;
    private int status;
    private String code;
    private String error;
    private String message;
    private String path;
    private Map<String, Object> details;

    public static ApiError of(int status, String code, String error, String message, String path, Map<String, Object> details) {
        return ApiError.builder()
                .timestamp(Instant.now())
                .status(status)
                .code(code)
                .error(error)
                .message(message)
                .path(path)
                .details(details)
                .build();
    }
}
