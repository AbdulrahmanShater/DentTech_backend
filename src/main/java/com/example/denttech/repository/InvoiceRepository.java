package com.example.denttech.repository;

import com.example.denttech.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "select invoice.* from invoice,_user,company where invoice.user_id=_user.id and company.id=_user.company_id and company.parent_type_code=?1  and invoice.sell=false", nativeQuery = true)
    List<Invoice> findInvoicesByLabAndSellFalse(Long company_id);
    @Query(value = "select invoice.* from invoice,_user,company where invoice.user_id=_user.id and company.id=_user.company_id and company.parent_type_code=?1 and invoice.sell=true", nativeQuery = true)
    List<Invoice> findInvoicesByLabAndSellTrue(Long company_id);
    @Query(value = "select Distinct(invoice.*) from invoice,_user,company where invoice.user_id=_user.id and _user.company_id=?1 and invoice.paid=false", nativeQuery = true)
    List<Invoice> findInvoicesByCompanyAndPaidFalse(Long company_id);

    @Query(value = "SELECT invoice.* FROM invoice,_user WHERE _user.company_id = ?1 AND invoice.invoice_date BETWEEN ?2 AND ?3 And invoice.user_id=_user.id",nativeQuery = true)
    List<Invoice> findByCompanyIdAndInvoiceDateBetween(Long company_id, Date begin,Date end);
    @Query(value = "SELECT invoice.* FROM invoice,_user WHERE _user.id = ?1 AND invoice.invoice_date BETWEEN ?2 AND ?3 And invoice.user_id=_user.id",nativeQuery = true)
    List<Invoice> findByUserIdAndInvoiceDateBetween(Long user_id, Date begin,Date end);
    @Query(value = "SELECT invoice.* FROM invoice,_user WHERE invoice.invoice_date BETWEEN ?1 AND ?2 ",nativeQuery = true)
    List<Invoice> findByInvoiceDateBetween( Date begin,Date end);

    @Modifying
    @Query(value = "UPDATE invoice  SET paid = true WHERE id IN ?1 AND paid =false",nativeQuery = true)
    void updateInvoiceById( List<Long> ids);
}
