package com.example.denttech.dto.request;

import com.example.denttech.model.InvoicePayment;
import com.example.denttech.model.PaymentMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class PaymentRequestDTO {
    @NotNull(message = "amount is required")
    private double amount;
    @NotNull(message = "paymentDate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate paymentDate;
    @NotBlank(message = "paymentNumber is required")
    private String paymentNumber;
    @NotBlank(message = "reference is required")
    private String reference;
    @NotNull(message = "invoicePayments is required")
    List<Long> invoicePayments;
    @NotNull(message = "paymentMode is required")
    private Long paymentMode;

}
