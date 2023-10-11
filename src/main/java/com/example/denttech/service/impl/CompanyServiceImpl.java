package com.example.denttech.service.impl;

import com.example.denttech.dto.request.CompanyRequestDTO;
import com.example.denttech.dto.response.CompanyResponseDTO;
import com.example.denttech.dto.response.PaymentResponseDTO;
import com.example.denttech.exception.EntityNotFoundException;
import com.example.denttech.model.Company;
import com.example.denttech.model.Invoice;
import com.example.denttech.model.InvoicePayment;
import com.example.denttech.model.User;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.repository.PaymentRepository;
import com.example.denttech.repository.UserRepository;
import com.example.denttech.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    final CompanyRepository companyRepository;
    final ModelMapper modelMapper;
    final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<CompanyResponseDTO> getAllCompanies(String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        List<CompanyResponseDTO> companies = new ArrayList<>();
        Set<PaymentResponseDTO> payments = new HashSet<>();
        for (Company company : companyRepository.findAll()) {
            if (company.getParent() == u.getCompany()) {
                CompanyResponseDTO companyResponseDTO = modelMapper.map(company, CompanyResponseDTO.class);
                double debit = 0, credit = 0;
                if (!company.isVendor()) {
                    for (User user : company.getUsers()) {
                        for (Invoice invoice : user.getInvoices()) {
                            debit += invoice.getTotal();
                            for (InvoicePayment invoicePayment : invoice.getInvoicePayments()) {
                                if (invoicePayment.getPayment() != null) {
                                    PaymentResponseDTO paymentResponseDTO = modelMapper.map(invoicePayment.getPayment(), PaymentResponseDTO.class);
                                    payments.add(paymentResponseDTO);
                                }
                            }
                        }
                    }
                } else {
                    for (User user : company.getUsers()) {
                        for (Invoice invoice : user.getInvoices()) {

                            debit += invoice.getTotal();
                            for (InvoicePayment invoicePayment : invoice.getInvoicePayments()) {
                                if (invoicePayment.getPayment() != null) {
                                    PaymentResponseDTO paymentResponseDTO = modelMapper.map(invoicePayment.getPayment(), PaymentResponseDTO.class);
                                    payments.add(paymentResponseDTO);
                                }
                            }

                        }
                    }
                }
                companyResponseDTO.setDebit(debit);
                for (PaymentResponseDTO paymentResponseDTO : payments)
                    credit += paymentResponseDTO.getAmount();
                companyResponseDTO.setPayments(List.of(modelMapper.map(paymentRepository.findById(1L)
                                                                                        .orElseThrow(), PaymentResponseDTO.class)));
                companyResponseDTO.setCredit(credit);
                companyResponseDTO.setBalance(debit - credit);
                if (debit - credit == 0) {
                    companyResponseDTO.setStatus("paid");
                } else {
                    companyResponseDTO.setStatus("unpaid");
                }
                companies.add(companyResponseDTO);
            }
        }

        return companies;
    }

    @Override
    public CompanyResponseDTO getCompanyById(Long id) {
        return modelMapper.map(companyRepository.findById(id)
                                                .orElseThrow(() -> new EntityNotFoundException("Customer not found")), CompanyResponseDTO.class);
    }

    @Override
    public CompanyResponseDTO saveCompany(CompanyRequestDTO customerDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        Company c = modelMapper.map(customerDTO, Company.class);
        c.setParent(u.getCompany());
        return modelMapper.map(companyRepository.save(c), CompanyResponseDTO.class);
    }

    @Override
    public CompanyResponseDTO editCompany(CompanyRequestDTO customerDTO, String username, Long id) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        Company c = companyRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        if (u.getCompany() == c.getParent()) {

            Company c1 = modelMapper.map(customerDTO, Company.class);
            c.setAddress(c1.getAddress());
            c.setEmail(c1.getEmail());
            c.setName(c1.getName());
            c.setTel(c1.getTel());
            c.setPoBox(c1.getPoBox());
            c.setPrice_stage(c1.getPrice_stage());
            c.setTRN(c1.getTRN());
            return modelMapper.map(companyRepository.save(c), CompanyResponseDTO.class);
        } else {
            throw new EntityNotFoundException("this company doesnt belong to you ");
        }


    }


    @Override
    public void deleteCompany(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        Company c = companyRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        if (u.getCompany() == c.getParent()) {
            companyRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("this company doesnt belong to you ");
        }
    }
}
