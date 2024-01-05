package com.example.denttech.service;

import com.example.denttech.dto.request.PaymentRequestDTO;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.PaymentResponseDTO;
import com.example.denttech.dto.response.UserResponseDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDTO> getPayments(String username);

    PaymentResponseDTO getPaymentById(Long id, String username);

    PaymentResponseDTO save(PaymentRequestDTO paymentRequestDTO, String username);

    void deletePayment(Long id, String username);

    PaymentResponseDTO editPayment(PaymentRequestDTO paymentRequestDTO, String username, Long id);

}
