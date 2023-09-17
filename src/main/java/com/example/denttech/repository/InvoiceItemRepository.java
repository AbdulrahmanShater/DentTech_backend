package com.example.denttech.repository;

import com.example.denttech.model.InvoiceItem;
import com.example.denttech.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long> {



}
