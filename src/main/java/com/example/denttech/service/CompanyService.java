package com.example.denttech.service;

import com.example.denttech.dto.request.CompanyRequestDTO;
import com.example.denttech.dto.response.CompanyResponseDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyResponseDTO> getAllCompanies(String username);

    CompanyResponseDTO getCompanyById(Long id);

    CompanyResponseDTO saveCompany(CompanyRequestDTO customerDTO, String username);
    CompanyResponseDTO editCompany(CompanyRequestDTO customerDTO, String username,Long id);

    void deleteCompany(Long id, String username);

    List<CompanyResponseDTO> getVendors(String username);

    List<CompanyResponseDTO> getBuyers(String username);

}
