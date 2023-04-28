package com.example.springexceptionhandler.controller;

import com.example.springexceptionhandler.controller.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> info(final Exception e) {
        log.info("", e);
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), e.getClass().getSimpleName()));
    }

    private ResponseEntity<ErrorResponse> error(final Exception e) {
        log.error("", e);
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(e.getMessage(), e.getClass().getSimpleName()));
    }

    /**
     * HttpRequestMethodNotSupportedException
     * MissingServletRequestParameterException
     * MethodArgumentNotValidException
     * HttpMediaTypeNotSupportedException
     * HttpMessageNotReadableException
     * MethodArgumentTypeMismatchException
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return info(e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return info(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return error(e);
    }
}
