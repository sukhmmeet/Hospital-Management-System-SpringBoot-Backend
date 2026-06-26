package com.dhaliwal.hospitalManagement.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(HttpStatus statusCode, String error, LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public ApiError(String error, HttpStatus statusCode) {
        this.statusCode = statusCode;
        this.error = error;
    }
}
