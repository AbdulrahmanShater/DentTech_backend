package com.example.denttech.repository;

import com.example.denttech.model.Company;
import com.example.denttech.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {



}
