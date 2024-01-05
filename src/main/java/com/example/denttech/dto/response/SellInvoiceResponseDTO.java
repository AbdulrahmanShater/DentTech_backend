package com.example.denttech.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data

public class SellInvoiceResponseDTO {
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
    private InvoiceUserResponseDTO user;

}
