package com.example.denttech.service.impl;

import com.example.denttech.dto.request.InvoiceItemRequestDTO;
import com.example.denttech.dto.request.SellInvoiceRequestDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;
import com.example.denttech.model.Invoice;
import com.example.denttech.model.InvoiceItem;
import com.example.denttech.model.User;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.repository.InvoiceRepository;
import com.example.denttech.repository.ItemRepository;
import com.example.denttech.repository.UserRepository;
import com.example.denttech.service.SellInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellInvoiceServiceImpl implements SellInvoiceService {

    final ItemRepository itemRepository;
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final CompanyRepository companyRepository;
    final InvoiceRepository invoiceRepository;


    @Override
    public List<SellInvoiceResponseDTO> getInvoices(String username) {

        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findInvoicesByLabAndSellTrue(u.getCompany()
                                                                .getId())
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, SellInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public List<SellInvoiceResponseDTO> getInvoicesByCompanyAndDate(String username, Long companyId, Date begin, Date end) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findByCompanyIdAndInvoiceDateBetween(companyId, begin, end)
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, SellInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }


    @Override
    public SellInvoiceResponseDTO getInvoiceById(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return modelMapper.map(invoiceRepository.findById(id).orElseThrow(), SellInvoiceResponseDTO.class);
    }

    @Override
    public SellInvoiceResponseDTO save(SellInvoiceRequestDTO sellInvoiceRequestDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        User c = userRepository.findById(sellInvoiceRequestDTO.getCustomer())
                               .orElseThrow();

        Invoice invoice = modelMapper.map(sellInvoiceRequestDTO, Invoice.class);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        double total = 0;
        for (InvoiceItemRequestDTO invoiceItemRequestDTO : sellInvoiceRequestDTO.getInvoiceItems()) {
            InvoiceItem invoiceItem = modelMapper.map(invoiceItemRequestDTO, InvoiceItem.class);
            invoiceItem.setItem(itemRepository.findById(invoiceItemRequestDTO.getItem())
                                              .orElseThrow());
            total += invoiceItem.getQuantity() * invoiceItem.getUnitPrice();
            invoiceItems.add(invoiceItem);
        }

        invoice.setInvoiceItems(invoiceItems);
        invoice.setUser(c);
        invoice.setSell(true);
        invoice.setTotal(total);
        Invoice i=invoiceRepository.save(invoice);
        i.setInvoiceNumber("INV_"+i.getId());
        return modelMapper.map(invoiceRepository.save(i), SellInvoiceResponseDTO.class);
    }

    @Override
    public void deleteInvoice(Long id, String username) {

    }

    @Override
    public SellInvoiceResponseDTO editInvoice(SellInvoiceRequestDTO sellInvoiceRequestDTO, String username, Long id) {
        return null;
    }

    @Override
    public List<SellInvoiceResponseDTO> getInvoiceByCompany(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findInvoicesByCompanyAndPaidFalse(id)
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, SellInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public List<SellInvoiceResponseDTO> getInvoicesByUserAndDate(String username, Long userId, Date beginDate, Date endDate) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findByUserIdAndInvoiceDateBetween(userId, beginDate, endDate)
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, SellInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public List<SellInvoiceResponseDTO> getInvoicesByDate(String username, Date beginDate, Date endDate) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return invoiceRepository.findByInvoiceDateBetween(beginDate, endDate)
                                .stream()
                                .map(invoice -> modelMapper.map(invoice, SellInvoiceResponseDTO.class))
                                .collect(Collectors.toList());
    }
}
