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

    @Column(length = 60, nullable = false)
    private String invoiceNumber;

    @Column( length = 60 )
    private String patientName;
    @Column( length = 60)
    private String fileNumber;
    @Column( length = 60)
    private String jobOrder;
    @Column( length = 60)
    private String Shade;
    @Column( length = 60, nullable = false)
    private boolean paid;
    @Column( length = 60, nullable = false)
    private boolean sell;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<Payment> payments;


    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> invoiceItems;
}
