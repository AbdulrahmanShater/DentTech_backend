package com.example.denttech.service;

import com.example.denttech.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO save(CompanyDTO customerDTO);
}
