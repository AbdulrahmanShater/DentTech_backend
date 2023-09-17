package com.example.denttech.repository;

import com.example.denttech.model.Account;
import com.example.denttech.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {



}
