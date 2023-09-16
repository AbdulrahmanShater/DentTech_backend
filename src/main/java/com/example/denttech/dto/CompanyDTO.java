package com.example.denttech.dto;

import com.example.denttech.annotation.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;


@Data

public class CompanyDTO {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @ValidPhoneNumber(message = "phoneNumber is invalid")
    private String tel;
    @NotBlank(message = "poBox is required")
    private String poBox;
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "TRN is required")
    private String TRN;
    @NotBlank(message = "address is required")
    private String address;
    private ArrayList<String> months;
    private double totalEarn;
    private double totalRemain;

}
