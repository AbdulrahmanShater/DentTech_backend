package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
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
public class Invoice {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 14, nullable = false)
    private String invoiceNumber;
    @Column(length = 60)
    private String patientName;
    @Column(length = 10)
    private String fileNumber;
    @Column(length = 10)
    private String jobOrder;
    @Column(length = 60)
    private String shade;
    @Column(nullable = false)
    private boolean paid;
    @Column(nullable = false)
    private boolean sell;
    @Column(length = 6, nullable = false)
    private double discount;
    @Column(length = 6, nullable = false)
    private double total;
    @Column(nullable = false, updatable = false)
    private LocalDate invoiceDate;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
    private List<InvoicePayment> invoicePayments;


    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> invoiceItems;

//    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
