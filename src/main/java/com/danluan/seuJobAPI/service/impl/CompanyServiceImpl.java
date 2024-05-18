package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.CompanyDTO;
import com.danluan.seuJobAPI.repository.CompanyRepository;
import com.danluan.seuJobAPI.repository.WorkerRepository;
import com.danluan.seuJobAPI.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(this::toDTO).toList();
    }

    @Override
    public CompanyDTO getCompanyById(Integer id) {
        return null;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        if(companyRepository.findByUserId(companyDTO.getUserId()).isPresent()) {
            throw new UserIdAlreadyInUse();
        }

        User user = userService.getUserById(companyDTO.getUserId());
        Company company = new Company(user);
        company.setCnpj(companyDTO.getCnpj());

        return this.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDTO toDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setCnpj(company.getCnpj());
        companyDTO.setUserId(company.getUser().getId());
        companyDTO.setLogin(company.getUser().getLogin());
        companyDTO.setName(company.getUser().getName());
        companyDTO.setEmail(company.getUser().getEmail());
        companyDTO.setPhone(company.getUser().getPhoneNumber());
        return companyDTO;
    }

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        return null;
    }
}
