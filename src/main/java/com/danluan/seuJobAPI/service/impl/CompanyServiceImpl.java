package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.CompanyDTO;
import com.danluan.seuJobAPI.model.dto.CompanyUpdateDTO;
import com.danluan.seuJobAPI.repository.CompanyRepository;
import com.danluan.seuJobAPI.repository.UserRepository;
import com.danluan.seuJobAPI.repository.WorkerRepository;
import com.danluan.seuJobAPI.service.CompanyService;
import com.danluan.seuJobAPI.service.JobService;
import com.danluan.seuJobAPI.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private JobService jobService;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(this::toDTO).toList();
    }

    @Override
    public CompanyDTO getCompanyById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Freelancer not found for ID: " + id));
        return toDTO(company);
    }

    @Override
    public Company getCompanyEntityById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found for ID: " + id));
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
    public CompanyDTO updateCompany(CompanyUpdateDTO companyUpdateDTO) {
        Company company = companyRepository.findById(companyUpdateDTO.getId()).orElse(null);
        if(company == null) {
            throw new EntityNotFoundException("Company not found for ID: " + companyUpdateDTO.getId());
        }

        company.setCnpj(companyUpdateDTO.getCnpj());
        User user = company.getUser();
        user.setName(companyUpdateDTO.getName());
        user.setEmail(companyUpdateDTO.getEmail());
        user.setPhoneNumber(companyUpdateDTO.getPhone());

        userRepository.save(user);

        return this.toDTO(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Integer id) {
        try {
            Company company = companyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Company not found for ID: " + id));
            companyRepository.delete(company);
        } catch (Exception e) {
            throw new EntityNotFoundException("Company not found for ID: " + id);
        }
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
        if(company.getJobs() != null)
            companyDTO.setJobs(company.getJobs().stream().map(jobService::toDTO).collect(Collectors.toList()));
        return companyDTO;
    }

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        return null;
    }
}
