package com.example.denttech.repository;

import com.example.denttech.model.Item;
import com.example.denttech.model.User;
import com.example.denttech.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {


    Optional<UserRole> findByName(String role);

}
