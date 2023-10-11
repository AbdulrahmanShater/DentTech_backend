package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.request.CompanyRequestDTO;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.response.CompanyResponseDTO;
import com.example.denttech.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    final CompanyService companyService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<CompanyResponseDTO>> createCompany(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid CompanyRequestDTO customerDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Company created successfully.", companyService.saveCompany(customerDTO, username)));
    }

    @PutMapping
    public ResponseEntity<DataResponse<List<CompanyResponseDTO>>> getCompanies(@RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Companies retrieved successfully.", companyService.getAllCompanies(username)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<CompanyResponseDTO>> getCompany(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company retrieved successfully.", companyService.getCompanyById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CompanyResponseDTO>> editCompany(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken, @RequestBody @Valid CompanyRequestDTO customerDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company edited successfully.", companyService.editCompany(customerDTO, username, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        companyService.deleteCompany(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);

    }



}
