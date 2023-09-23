package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Payment {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 60, nullable = false)
    private double amount;
    @Column( length = 60, nullable = false)
    private LocalDate paymentDate;
    @Column( length = 60, nullable = false)
    private String paymentNumber;
    @Column( length = 60, nullable = false)
    private String reference;

    @OneToMany(mappedBy = "payment")
    List<InvoicePayment> invoicePayments;
    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;

}
