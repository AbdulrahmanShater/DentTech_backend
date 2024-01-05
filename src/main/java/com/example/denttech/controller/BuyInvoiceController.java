package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.request.BuyInvoiceRequestDTO;
import com.example.denttech.dto.request.CompanyRequestDTO;
import com.example.denttech.dto.response.BuyInvoiceResponseDTO;
import com.example.denttech.dto.response.CompanyResponseDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;
import com.example.denttech.service.CompanyService;
import com.example.denttech.service.BuyInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buy-invoice")
@RequiredArgsConstructor
public class BuyInvoiceController {
    final BuyInvoiceService buyInvoiceService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<BuyInvoiceResponseDTO>> createBuyInvoice(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid BuyInvoiceRequestDTO buyInvoiceRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Company created successfully.", buyInvoiceService.save(buyInvoiceRequestDTO, username)));
    }

    @PutMapping
    public ResponseEntity<DataResponse<List<BuyInvoiceResponseDTO>>> getBuyInvoices(@RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Companies retrieved successfully.", buyInvoiceService.getInvoices(username)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<BuyInvoiceResponseDTO>> getBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company retrieved successfully.", buyInvoiceService.getInvoiceById(id, username)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<BuyInvoiceResponseDTO>> editBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken, @RequestBody @Valid BuyInvoiceRequestDTO buyInvoiceRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company edited successfully.", buyInvoiceService.editInvoice(buyInvoiceRequestDTO, username, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        buyInvoiceService.deleteInvoice(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);

    }


}
