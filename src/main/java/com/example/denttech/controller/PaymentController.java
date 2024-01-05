package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.request.PaymentRequestDTO;
import com.example.denttech.dto.request.SellInvoiceRequestDTO;
import com.example.denttech.dto.response.PaymentResponseDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;
import com.example.denttech.service.CompanyService;
import com.example.denttech.service.PaymentService;
import com.example.denttech.service.SellInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    final CompanyService companyService;
    final SellInvoiceService sellInvoiceService;
    final PaymentService paymentService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<PaymentResponseDTO>> createPayment(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Company created successfully.", paymentService.save(paymentRequestDTO, username)));
    }

    @PutMapping
    public ResponseEntity<DataResponse<List<PaymentResponseDTO>>> getPayment(@RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Companies retrieved successfully.", paymentService.getPayments(username)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<SellInvoiceResponseDTO>> getBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company retrieved successfully.", sellInvoiceService.getInvoiceById(id, username)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<SellInvoiceResponseDTO>> editBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken, @RequestBody @Valid SellInvoiceRequestDTO sellInvoiceRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company edited successfully.", sellInvoiceService.editInvoice(sellInvoiceRequestDTO, username, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        sellInvoiceService.deleteInvoice(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);

    }

}
