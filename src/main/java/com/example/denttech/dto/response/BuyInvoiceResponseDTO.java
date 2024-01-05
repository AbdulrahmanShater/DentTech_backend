package com.example.denttech.dto.response;

import com.example.denttech.model.InvoiceItem;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data

public class BuyInvoiceResponseDTO {
    private Long id;
    private boolean paid;
    private boolean sell;
    private double discount;
    private double total;
    private LocalDate invoiceDate;
    private String reference;
    private String invoiceNumber;
    private List<InvoiceItemResponseDTO> invoiceItems;
    private InvoiceUserResponseDTO user;

}
