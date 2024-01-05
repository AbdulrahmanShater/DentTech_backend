package com.example.denttech.dto.response;

import lombok.Data;

import java.time.LocalDate;
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
//    private List<PaymentResponseDTO> payments;


}

@Data
class CompanyUserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private List<SellCompanyInvoiceResponseDTO> Invoices;

}

@Data
class SellCompanyInvoiceResponseDTO {
    private Long id;
    private String invoiceNumber;
    private String patientName;
    private String fileNumber;
    private String jobOrder;
    private String shade;
    private boolean paid;
    private boolean sell;
    private double discount;
    private double total;

    private LocalDate invoiceDate;

    //    private List<InvoicePayment> invoicePayments;
    private List<InvoiceItemResponseDTO> invoiceItems;

}
