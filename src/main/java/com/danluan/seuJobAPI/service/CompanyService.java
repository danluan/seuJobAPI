package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.dto.CompanyDTO;
import com.danluan.seuJobAPI.model.dto.CompanyUpdateDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getAllCompanies();
    public CompanyDTO getCompanyById(Integer id);
    public Company getCompanyEntityById(Integer id);
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(CompanyUpdateDTO companyUpdateDTO);
    void deleteCompany(Integer id);
    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);
}
