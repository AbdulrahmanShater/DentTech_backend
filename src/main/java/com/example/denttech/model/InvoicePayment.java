package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvoicePayment {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @Column(length = 6,nullable = false)
    private double amount;
//    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
