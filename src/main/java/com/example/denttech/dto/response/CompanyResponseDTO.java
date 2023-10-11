package com.example.denttech.dto.response;

import lombok.Data;

import java.util.List;


@Data

public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String tel;
    private String poBox;
    private String email;
    private String TRN;
    private String address;
    private double debit;
    private double credit;
    private double balance;
    private String status;
    private boolean vendor;
    private int price_stage;
    private List<CompanyUserResponseDTO> users;
    private List<PaymentResponseDTO> payments;


}
