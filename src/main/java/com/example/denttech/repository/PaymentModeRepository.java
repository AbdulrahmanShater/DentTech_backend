package com.example.denttech.repository;

import com.example.denttech.model.Company;
import com.example.denttech.model.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {


}
