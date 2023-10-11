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
//TODO make combined unique the email and the id
public class Company {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 15, nullable = false)
    private String tel;
    @Column(length = 8)
    private String poBox;
    @Column(length = 320, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private String TRN;
    @Column(length = 200, nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean vendor;
    @Column(nullable = false)
    private int price_stage;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<User> users;


    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Item> items;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "parentTypeCode")
    private Company parent;
    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private List<Company> children;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt;
}
