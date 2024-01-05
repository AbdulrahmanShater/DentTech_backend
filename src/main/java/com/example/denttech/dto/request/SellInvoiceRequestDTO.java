package com.example.denttech.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Data

public class SellInvoiceRequestDTO {

//    @NotBlank(message = "invoiceNumber is required")
//    private String invoiceNumber;
    @NotBlank(message = "patientName is required")
    private String patientName;
    @NotBlank(message = "fileNumber is required")
    private String fileNumber;
    @NotBlank(message = "jobOrder is required")
    private String jobOrder;
    @NotBlank(message = "shade is required")
    private String shade;
    @NotNull(message = "discount is required")
    private Double discount;
    @NotNull(message = "customer is required")
    private Long customer;
    @NotNull(message = "invoiceItems is required")
    @NotEmpty(message = "invoiceItems is Empty")
    private List<InvoiceItemRequestDTO> invoiceItems;
    @NotNull(message = "invoiceDate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

}
