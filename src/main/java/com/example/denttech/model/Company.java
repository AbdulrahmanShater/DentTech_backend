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

public class Company {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 60, nullable = false)
    private String name;
    @Column( length = 60, nullable = false)
    private String tel;
    @Column( length = 60, nullable = false)
    private String poBox;
    @Column( length = 60, nullable = false)
    private String email;
    @Column( length = 60, nullable = false)
    private String TRN;
    @Column( length = 60, nullable = false)
    private String address;
    @Column( length = 60)
    private boolean vendor;
    @Column( length = 60)
    private Long price_stage;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "parentTypeCode")
    protected Company parent;

    @OneToMany(mappedBy = "parent")
    protected List<Company> children;


}
