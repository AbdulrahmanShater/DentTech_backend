package com.example.denttech.exception;

import com.example.springboottest.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("not found", ex.getMessage(), "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", ex.getMessage(), "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> error = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", error.toString(), "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String msg;
        if (Objects.requireNonNull(ex.getMessage()).contains("uk_phonenumber")) {
            msg = "phone number is exist.";
        } else if (Objects.requireNonNull(ex.getMessage()).contains("uk_email")) {
            msg = "email is exist.";
        } else if (Objects.requireNonNull(ex.getMessage()).contains("uk_customer_address_line")) {
            msg = "addressLine already exist.";
        } else {
            msg = ex.getMessage();
        }
        ErrorResponse errorResponse = new ErrorResponse("Conflict", msg, "409");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
