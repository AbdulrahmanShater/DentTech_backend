package com.example.denttech.service;

import com.example.denttech.dto.request.SellInvoiceRequestDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;

import java.util.Date;
import java.util.List;

public interface SellInvoiceService {
    List<SellInvoiceResponseDTO> getInvoices(String username);
    List<SellInvoiceResponseDTO> getInvoicesByCompanyAndDate(String username, Long companyId, Date begin, Date end);

    SellInvoiceResponseDTO getInvoiceById(Long id, String username);

    SellInvoiceResponseDTO save(SellInvoiceRequestDTO sellInvoiceRequestDTO, String username);

    void deleteInvoice(Long id, String username);

    SellInvoiceResponseDTO editInvoice(SellInvoiceRequestDTO sellInvoiceRequestDTO, String username, Long id);

    List<SellInvoiceResponseDTO>  getInvoiceByCompany(Long id, String username);

    List<SellInvoiceResponseDTO>  getInvoicesByUserAndDate(String username, Long userId, Date beginDate, Date endDate);

    List<SellInvoiceResponseDTO>  getInvoicesByDate(String username, Date beginDate, Date endDate);
}
