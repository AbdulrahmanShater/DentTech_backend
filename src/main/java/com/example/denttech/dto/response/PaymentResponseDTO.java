package com.example.denttech.dto.response;

import com.example.denttech.model.InvoicePayment;
import com.example.denttech.model.PaymentMode;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data

public class PaymentResponseDTO {
    private Long id;
    private double amount;
    private LocalDate paymentDate;
    private String paymentNumber;
    private String reference;
    private String paymentMode;
}
