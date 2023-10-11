package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Item {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 60, nullable = false)
    private String name;
    @Column( length = 60, nullable = false)
    private String description;
    @Column( length = 6, nullable = false)
    private double price1;
    @Column( length = 6, nullable = false)
    private double price2;
    @Column( length = 6, nullable = false)
    private double price3;
    @Column( length = 6, nullable = false)
    private double price4;

    @ToString.Exclude
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<InvoiceItem> invoiceItems;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
