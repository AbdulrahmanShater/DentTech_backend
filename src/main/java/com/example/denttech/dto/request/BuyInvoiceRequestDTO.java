package com.example.denttech.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Data

public class BuyInvoiceRequestDTO {
    @NotNull(message = "discount is required")
    private Double discount;

    @NotBlank(message = "reference is required")
    private String reference;
    @NotNull(message = "customer is required")
    private Long customer;
    @NotNull(message = "invoiceItems is required")
    @NotEmpty(message = "invoiceItems is empty")
    private List<InvoiceItemRequestDTO> invoiceItems;
    @NotNull(message = "invoiceDate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

}
