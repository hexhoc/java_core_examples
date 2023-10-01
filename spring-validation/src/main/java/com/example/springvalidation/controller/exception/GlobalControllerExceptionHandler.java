package com.example.springvalidation.controller.exception;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> processValidationError(IllegalArgumentException e) {
        log.log(Level.INFO, "Returning HTTP 400 Bad Request", e);

        ApiError apiError = new ApiError("Returning HTTP 400 Bad Request", e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> processMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.log(Level.INFO, "Returning HTTP 400 Bad Request", e);

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = new ApiError("Method Argument Not Valid", e.getMessage(), errors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> processRequestBodyNotValid(ConstraintViolationException e) {
        log.log(Level.INFO, "Returning HTTP 400 Bad Request", e);

        List<String> errors = e.getConstraintViolations()
                               .stream()
                               .map(ConstraintViolation::getMessage)
                               .collect(Collectors.toList());

        ApiError apiError = new ApiError("Method Argument Not Valid", e.getMessage(), errors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleAllExceptions(Exception e) {
        log.log(Level.INFO, "Returning HTTP 500 INTERNAL SERVER ERROR", e);

        ApiError apiError = new ApiError("Internal Exception", e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}