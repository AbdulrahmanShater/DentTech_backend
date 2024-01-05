package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.request.SellInvoiceRequestDTO;
import com.example.denttech.dto.response.SellInvoiceResponseDTO;
import com.example.denttech.service.CompanyService;
import com.example.denttech.service.SellInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sell-invoice")
@RequiredArgsConstructor
public class SellInvoiceController {
    final CompanyService companyService;
    final SellInvoiceService sellInvoiceService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<SellInvoiceResponseDTO>> createBuyInvoice(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid SellInvoiceRequestDTO sellInvoiceRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Company created successfully.", sellInvoiceService.save(sellInvoiceRequestDTO, username)));
    }

//    @PutMapping
//    public ResponseEntity<DataResponse<List<SellInvoiceResponseDTO>>> getBuyInvoices(@RequestHeader("Authorization") String jwtToken) {
//        String username = jwtService.extractUsername(jwtToken.substring(7));
//return ResponseEntity.status(HttpStatus.OK)
//                                 .body(new DataResponse<>("success", "Companies retrieved successfully.", sellInvoiceService.getInvoices(username)));
//    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<SellInvoiceResponseDTO>> getBuyInvoice(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company retrieved successfully.", sellInvoiceService.getInvoiceById(id, username)));
    }
    @PostMapping("/company/{id}")
    public ResponseEntity<DataResponse<List<SellInvoiceResponseDTO>>> getBuyInvoiceByCompany(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company retrieved successfully.", sellInvoiceService.getInvoiceByCompany(id, username)));
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
    @PutMapping()
    public ResponseEntity<DataResponse<List<SellInvoiceResponseDTO>>> getInvoicesByCompanyAndDate(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestHeader("Authorization") String jwtToken
    ) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        System.out.println("companyid =     "+companyId+"   beginDate  "+beginDate+"   endDate   "+endDate );
        if(userId!=null&&companyId!=null&&beginDate!=null&&endDate!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new DataResponse<>("success", "Companies retrieved successfully.", sellInvoiceService.getInvoicesByUserAndDate(username, userId, beginDate, endDate)));

        }
        else if(companyId!=null&&beginDate!=null&&endDate!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new DataResponse<>("success", "Companies retrieved successfully.", sellInvoiceService.getInvoicesByCompanyAndDate(username, companyId, beginDate, endDate)));

        }
        else if(beginDate!=null&&endDate!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new DataResponse<>("success", "Companies retrieved successfully.", sellInvoiceService.getInvoicesByDate(username, beginDate, endDate)));
        }
        else{

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new DataResponse<>("success", "Companies retrieved successfully.", sellInvoiceService.getInvoices(username)));
        }
    }

}
