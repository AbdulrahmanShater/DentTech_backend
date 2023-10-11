package com.example.denttech.dto.response;

import lombok.Data;

import java.util.List;


@Data

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private UserCompanyResponseDTO company;
    private List<InvoiceResponseDTO> Invoices;

}
