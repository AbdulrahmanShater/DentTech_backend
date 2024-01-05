package com.example.denttech.service.impl;

import com.example.denttech.dto.request.BuyInvoiceRequestDTO;
import com.example.denttech.dto.request.InvoiceItemRequestDTO;
import com.example.denttech.dto.response.BuyInvoiceResponseDTO;
import com.example.denttech.exception.EntityNotFoundException;
import com.example.denttech.model.*;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.repository.InvoiceRepository;
import com.example.denttech.repository.ItemRepository;
import com.example.denttech.repository.UserRepository;
import com.example.denttech.service.BuyInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyInvoiceServiceImpl implements BuyInvoiceService {

    final ItemRepository itemRepository;
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final CompanyRepository companyRepository;
    final InvoiceRepository invoiceRepository;


    @Override
    public List<BuyInvoiceResponseDTO> getInvoices(String username) {

        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findInvoicesByLabAndSellFalse(u.getCompany()
                                                                .getId())
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, BuyInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public BuyInvoiceResponseDTO getInvoiceById(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        return modelMapper.map(invoiceRepository.findById(id)
                                                .orElseThrow(() -> new EntityNotFoundException("Customer not found")), BuyInvoiceResponseDTO.class);
    }

    @Override
    public BuyInvoiceResponseDTO save(BuyInvoiceRequestDTO buyInvoiceRequestDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        User c = userRepository.findById(buyInvoiceRequestDTO.getCustomer())
                               .orElseThrow();

        Invoice invoice = modelMapper.map(buyInvoiceRequestDTO, Invoice.class);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        double total = 0;
        for (InvoiceItemRequestDTO invoiceItemRequestDTO : buyInvoiceRequestDTO.getInvoiceItems()) {
            InvoiceItem invoiceItem = modelMapper.map(invoiceItemRequestDTO, InvoiceItem.class);
            invoiceItem.setItem(itemRepository.findById(invoiceItemRequestDTO.getItem())
                                              .orElseThrow());
            total += invoiceItem.getQuantity() * invoiceItem.getUnitPrice();
            invoiceItems.add(invoiceItem);
        }

        invoice.setInvoiceItems(invoiceItems);
        invoice.setUser(c);
        invoice.setTotal(total);

        invoice.setSell(false);
        Invoice i=invoiceRepository.save(invoice);
        i.setInvoiceNumber("B_INV_"+i.getId());


        return modelMapper.map(invoiceRepository.save(i), BuyInvoiceResponseDTO.class);

    }

    @Override
    public void deleteInvoice(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        companyRepository.deleteById(id);
    }

    @Override
    public BuyInvoiceResponseDTO editInvoice(BuyInvoiceRequestDTO buyInvoiceRequestDTO, String username, Long id) {
        return null;
    }
}
