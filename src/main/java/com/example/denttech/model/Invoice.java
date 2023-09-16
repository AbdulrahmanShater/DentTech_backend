package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 60, nullable = false)
    private String invoiceNumber;

    @Column(unique = true, length = 60 )
    private String patientName;
    @Column(unique = true, length = 60)
    private String fileNumber;
    @Column(unique = true, length = 60)
    private String jobOrder;
    @Column(unique = true, length = 60)
    private String Shade;
    @Column(unique = true, length = 60, nullable = false)
    private boolean paid;
    @Column(unique = true, length = 60, nullable = false)
    private boolean sell;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> invoiceItems;
}
