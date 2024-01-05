package com.example.denttech.dto.request;

import com.example.denttech.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class UserRequestDTO {
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @ValidPhoneNumber(message = "phoneNumber is invalid")
    private String tel;
    @Email(message = "email is invalid")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotNull(message = "company is required")
    private Long company;
    //    @NotNull(message = "userRole is required")
    //    private Long userRole;

}
