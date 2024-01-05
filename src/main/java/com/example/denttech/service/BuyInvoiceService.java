package com.example.denttech.service;

import com.example.denttech.dto.request.BuyInvoiceRequestDTO;
import com.example.denttech.dto.request.SellInvoiceRequestDTO;
import com.example.denttech.dto.response.BuyInvoiceResponseDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;

import java.util.List;

public interface BuyInvoiceService {
    List<BuyInvoiceResponseDTO> getInvoices(String username);

    BuyInvoiceResponseDTO getInvoiceById(Long id, String username);

    BuyInvoiceResponseDTO save(BuyInvoiceRequestDTO buyInvoiceRequestDTO, String username);

    void deleteInvoice(Long id, String username);

    BuyInvoiceResponseDTO editInvoice(BuyInvoiceRequestDTO buyInvoiceRequestDTO, String username, Long id);
}
