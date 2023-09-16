package com.example.denttech.controller;

import com.example.denttech.dto.CompanyDTO;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<CompanyDTO>> createCompany(@RequestBody @Valid CompanyDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>("success", "Customer created successfully.", companyService.save(customerDTO)));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<CompanyDTO>>> getCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customers retrieved successfully.", companyService.getCompanies()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CompanyDTO>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customer retrieved successfully.", companyService.getCompanyById(id)));
    }

}
