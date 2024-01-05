package com.example.denttech.exception;

import com.example.denttech.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>("not found", ex.getMessage(), "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse<String>> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>("Bad Request", ex.getMessage(), "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<HashMap<String, String>>> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println("binding " + ex.getBindingResult()
                                          .getFieldErrors() + "finish");
        HashMap<String,String> errorList=new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult()
                                       .getFieldErrors()) {
            errorList.putIfAbsent(fieldError.getField(),fieldError.getDefaultMessage());
        }
//        List<String> error = ex.getBindingResult()
//                               .getFieldErrors()
//                               .stream()
//                               .map(FieldError::getDefaultMessage)
//                               .toList();
        ErrorResponse<HashMap<String, String>> errorResponse = new ErrorResponse<>("Bad Request",errorList, "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String msg;
        if (Objects.requireNonNull(ex.getMessage())
                   .contains("uk_phonenumber")) {
            msg = "phone number is exist.";
        } else if (Objects.requireNonNull(ex.getMessage())
                          .contains("uk_email")) {
            msg = "email is exist.";
        } else if (Objects.requireNonNull(ex.getMessage())
                          .contains("uk_customer_address_line")) {
            msg = "addressLine already exist.";
        } else {
            msg = ex.getMessage();
        }
        ErrorResponse<String> errorResponse = new ErrorResponse<>("Conflict", msg, "409");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse<String>> handleNoSuchElementException() {
        ErrorResponse<String> errorResponse = new ErrorResponse<>("not found", "ex.getMessage()", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(errorResponse);

    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse<String>> handleExpiredJwtException() {
        ErrorResponse<String> errorResponse = new ErrorResponse<>("not found", "Token Not valid", "401");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                             .body(errorResponse);

    }
}
