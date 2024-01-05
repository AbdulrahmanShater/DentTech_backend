package com.example.denttech.repository;

import com.example.denttech.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {


    List<Company> findByVendor(boolean vendor);
    @Query(value = "SELECT SUM(i.total) AS total_invoice_value FROM company c INNER JOIN _user u ON c.id = u.company_id inner JOIN invoice i ON u.id = i.user_id where c.id= ?1 GROUP BY c.id LIMIT 1",nativeQuery = true)
    Optional<Double> findDebitByCompanyId(Long id);

    @Query(value = "SELECT SUM(ps.amount) AS total_invoice_value FROM company c INNER JOIN _user u ON c.id = u.company_id inner JOIN invoice i ON u.id = i.user_id INNER JOIN invoice_payment ON invoice_payment.invoice_id = i.id INNER JOIN payment ps ON ps.id = invoice_payment.payment_id where c.id=?1 GROUP BY c.id LIMIT 1",nativeQuery = true)
    Optional<Double> findCreditByCompanyId(Long id);

}
