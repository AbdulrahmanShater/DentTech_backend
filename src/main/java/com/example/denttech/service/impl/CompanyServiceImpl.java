package com.example.denttech.service.impl;

import com.example.denttech.dto.CompanyDTO;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getCompanies() {
        return null;
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        return null;
    }

    @Override
    public CompanyDTO save(CompanyDTO customerDTO) {
        return null;
    }
}
