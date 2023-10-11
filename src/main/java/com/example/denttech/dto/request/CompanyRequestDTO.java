package com.example.denttech.dto.request;

import com.example.denttech.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data

public class CompanyRequestDTO {
    @Length(max = 60, min = 3, message = "name max length 60 and min is 3")

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "phoneNumber is required")
    @ValidPhoneNumber(message = "phoneNumber is invalid")
    private String tel;
    @NotBlank(message = "poBox is required")
    @Length(max = 60, min = 3, message = "PO")
    private String poBox;
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "trn is required")
    @Length(max = 15,min = 15,message = "TRN number must be 15 digits")
    private String trn;
    @NotBlank(message = "address is required")
    private String address;
    @NotNull(message = "vendor is required")
    private Boolean vendor;
    @NotNull(message = "price_stage is required")
    @Min(value = 0, message = "Value must be at least 0")
    @Max(value = 3, message = "Value must be at most 3")
    private Integer price_stage;


}
