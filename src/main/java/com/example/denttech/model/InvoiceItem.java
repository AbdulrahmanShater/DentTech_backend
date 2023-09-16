package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceItem {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 60, nullable = false)
    private int quantity;
    @Column(unique = true, length = 60, nullable = false)
    private double unitPrice;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice Invoice;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
