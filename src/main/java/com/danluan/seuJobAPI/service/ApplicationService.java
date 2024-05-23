package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.dto.ApplicationCreateDTO;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.model.dto.CompanyDTO;
import com.danluan.seuJobAPI.model.dto.CompanyUpdateDTO;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDTO> getAllApplications();
    public ApplicationDTO getApplicationById(Integer id);
    ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO);
    ApplicationDTO updateApplication(ApplicationDTO applicationDTO);
    void deleteApplication(Integer id);
    ApplicationDTO toDTO(Application application);
    Application toEntity(ApplicationDTO applicationDTO);
}
