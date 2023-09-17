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

public class Item {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 60, nullable = false)
    private String name;
    @Column( length = 60, nullable = false)
    private String description;
    @Column( length = 60, nullable = false)
    private double price1;
    @Column( length = 60, nullable = false)
    private double price2;
    @Column( length = 60, nullable = false)
    private double price3;
    @Column( length = 60, nullable = false)
    private double price4;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<InvoiceItem> invoiceItems;
}
