package com.example.denttech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvoiceItem {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 3, nullable = false)
    private int quantity;
    @Column( length = 6, nullable = false)
    private double unitPrice;
    @ToString.Exclude

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice Invoice;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
//    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
