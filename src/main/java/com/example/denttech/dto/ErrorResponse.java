package com.example.denttech.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse<T> {
    private String status;
    private T errors;
    private String errorCode;

}
