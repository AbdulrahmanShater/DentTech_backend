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

public class Laboratory {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 60, nullable = false)
    private String name;
    @Column(unique = true, length = 60, nullable = false)
    private String tel;
    @Column(unique = true, length = 60, nullable = false)
    private String poBox;
    @Column(unique = true, length = 60, nullable = false)
    private String email;
    @Column(unique = true, length = 60, nullable = false)
    private String TRN;
    @Column(unique = true, length = 60, nullable = false)
    private String address;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "lab_id")
    private List<Account> accounts;
}
