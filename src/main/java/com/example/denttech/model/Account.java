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

public class Account {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 60, nullable = false)
    private String firstName;
    @Column(unique = true, length = 60, nullable = false)
    private String lastName;
    @Column(unique = true, length = 60, nullable = false)
    private String email;
    @Column(unique = true, length = 60, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratory laboratory;

}
