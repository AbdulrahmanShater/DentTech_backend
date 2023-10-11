package com.example.denttech.dto.response;

import lombok.Data;

import java.util.List;


@Data

public class CompanyUserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private List<InvoiceResponseDTO> Invoices;

}
