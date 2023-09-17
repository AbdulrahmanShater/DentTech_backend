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

public class Account {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 60, nullable = false)
    private String firstName;
    @Column( length = 60, nullable = false)
    private String lastName;
    @Column( length = 60, nullable = false)
    private String email;
    @Column( length = 60, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Invoice> Invoices;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
}
