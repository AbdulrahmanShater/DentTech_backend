package com.example.denttech.repository;

import com.example.denttech.model.Payment;
import com.example.denttech.model.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
