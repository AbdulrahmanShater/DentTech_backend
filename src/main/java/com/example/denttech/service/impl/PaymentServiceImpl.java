package com.example.denttech.service.impl;

import com.example.denttech.dto.request.PaymentRequestDTO;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.ItemResponseDTO;
import com.example.denttech.dto.response.PaymentResponseDTO;
import com.example.denttech.dto.response.UserResponseDTO;
import com.example.denttech.exception.EntityNotFoundException;
import com.example.denttech.model.*;
import com.example.denttech.repository.*;
import com.example.denttech.service.PaymentService;
import com.example.denttech.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final CompanyRepository companyRepository;
    final PaymentRepository paymentRepository;
    final PaymentModeRepository paymentModeRepository;
    final InvoicePaymentRepository invoicePaymentRepository;
    final InvoiceRepository invoiceRepository;


    @Override
    public List<PaymentResponseDTO> getPayments(String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        return paymentRepository.findAll()
                                .stream()
                                .map(item -> modelMapper.map(item, PaymentResponseDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id, String username) {
        return null;
    }

    @Transactional
    @Override
    public PaymentResponseDTO save(PaymentRequestDTO paymentRequestDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        Payment payment = modelMapper.map(paymentRequestDTO, Payment.class);
        payment.setPaymentMode(paymentModeRepository.findById(paymentRequestDTO.getPaymentMode())
                                                    .orElseThrow());
        invoiceRepository.updateInvoiceById(paymentRequestDTO.getInvoicePayments());

        Payment p = paymentRepository.save(payment);


        invoiceRepository.findAllById(paymentRequestDTO.getInvoicePayments())
                         .forEach(invoice -> {
                             InvoicePayment invoicePayment = new InvoicePayment();
                             invoicePayment.setPayment(p);
                             invoicePayment.setInvoice(invoice);
                             invoicePayment.setAmount(invoice.getTotal());
                             invoicePaymentRepository.save(invoicePayment);
                         });


        return modelMapper.map(p, PaymentResponseDTO.class);
    }

    @Override
    public void deletePayment(Long id, String username) {

    }

    @Override
    public PaymentResponseDTO editPayment(PaymentRequestDTO paymentRequestDTO, String username, Long id) {
        return null;
    }


}
