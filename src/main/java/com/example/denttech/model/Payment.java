package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @Column(unique = true, length = 60, nullable = false)
    private double amount;
    @Column(unique = true, length = 60, nullable = false)
    private LocalDate paymentDate;
    @Column(unique = true, length = 60, nullable = false)
    private String paymentNumber;
    @Column(unique = true, length = 60, nullable = false)
    private String reference;

    @ManyToOne
    @JoinColumn(name = "comapny_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;

}
