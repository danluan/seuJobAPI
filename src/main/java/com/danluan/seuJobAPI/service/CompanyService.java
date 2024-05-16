package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> getAllCompanies();
    public Company getCompanyById(Integer id);
    public Company createCompany(Company company);
    public Company updateCompany(Company company);
    public void deleteCompany(Integer id);
}
