package com.example.denttech.repository;

import com.example.denttech.model.Company;
import com.example.denttech.model.Item;
import com.example.denttech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByCompany(Company c);


}
