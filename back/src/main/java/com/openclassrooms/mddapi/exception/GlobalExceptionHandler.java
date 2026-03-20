package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler that intercepts exceptions thrown by any controller
 * and maps them to structured HTTP error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link RuntimeException} and returns an HTTP 400 response with the error message.
     *
     * @param e the caught runtime exception
     * @return HTTP 400 with a JSON body containing the error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles bean validation failures ({@link MethodArgumentNotValidException})
     * and returns an HTTP 400 response with the first field error message.
     *
     * @param e the caught validation exception
     * @return HTTP 400 with a JSON body containing the first validation error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}