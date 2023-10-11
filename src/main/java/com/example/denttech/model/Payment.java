package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Builder
public class Payment {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 6, nullable = false)
    private double amount;
    @Column(nullable = false)
    private LocalDate paymentDate;
    @Column(length = 14, nullable = false)
    private String paymentNumber;
    @Column(length = 60)
    private String reference;
    @JsonIgnore
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    List<InvoicePayment> invoicePayments;
    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;
    //    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
