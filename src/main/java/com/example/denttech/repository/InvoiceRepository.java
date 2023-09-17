package com.example.denttech.repository;

import com.example.denttech.model.Invoice;
import com.example.denttech.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {



}
