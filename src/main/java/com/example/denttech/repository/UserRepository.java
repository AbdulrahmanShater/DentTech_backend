package com.example.denttech.repository;

import com.example.denttech.model.Company;
import com.example.denttech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    @Query(value = "select u.* from _user u, company c where u.company_id=c.id and u.user_role_id=?1 and c.vendor=?2 and c.parent_type_code=?3", nativeQuery = true)
    List<User> findByVendor(long role,boolean vendor,long parent_type_code);

}
