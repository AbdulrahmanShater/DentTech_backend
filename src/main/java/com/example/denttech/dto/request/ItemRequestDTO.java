package com.example.denttech.dto.request;

import com.example.denttech.annotation.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class ItemRequestDTO {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotNull(message = "price1 is required")
    private double price1;
    @NotNull(message = "price2 is required")
    private double price2;
    @NotNull(message = "price3 is required")
    private double price3;
    @NotNull(message = "price4 is required")
    private double price4;


}
