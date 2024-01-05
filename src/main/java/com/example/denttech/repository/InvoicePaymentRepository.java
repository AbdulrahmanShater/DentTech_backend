package com.example.denttech.repository;

import com.example.denttech.model.InvoiceItem;
import com.example.denttech.model.InvoicePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicePaymentRepository extends JpaRepository<InvoicePayment,Long> {



}
