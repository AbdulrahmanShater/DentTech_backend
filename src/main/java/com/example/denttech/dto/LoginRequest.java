package com.example.denttech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "password is invalid")
    private String password;
}
