package com.rsuniverse.jobify_notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rsuniverse.jobify_notification.models.responses.BaseRes;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseRes<String>> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return BaseRes.error(ex.getMessage(), 400, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseRes<String>> handleGlobalException(Exception ex, WebRequest request) {
        return BaseRes.error(ex.getMessage(), 500, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
