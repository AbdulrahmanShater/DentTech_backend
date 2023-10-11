package com.example.denttech.dto.request;

import com.example.denttech.annotation.ValidPhoneNumber;
import com.example.denttech.model.InvoiceItem;
import com.example.denttech.model.InvoicePayment;
import com.example.denttech.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;


@Data

public class InvoiceRequestDTO {
    @NotBlank(message = "invoiceNumber is required")
    private String invoiceNumber;
    @NotBlank(message = "patientName is required")
    private String patientName;
    @NotBlank(message = "fileNumber is required")
    private String fileNumber;
    @NotBlank(message = "jobOrder is required")
    private String jobOrder;
    @NotBlank(message = "firstName is required")
    private String shade;
    @NotBlank(message = "firstName is required")
    private boolean paid;
    @NotBlank(message = "firstName is required")
    private boolean sell;
    @NotBlank(message = "firstName is required")
    private double discount;
    @NotBlank(message = "firstName is required")
    private double total;
    @NotBlank(message = "firstName is required")
    private LocalDate invoiceDate;
    @NotBlank(message = "firstName is required")
    private User user;

    private List<InvoicePayment> invoicePayments;


    private List<InvoiceItem> invoiceItems;

}
