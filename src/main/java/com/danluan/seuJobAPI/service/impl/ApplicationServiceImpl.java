package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.model.dto.CompanyDTO;
import com.danluan.seuJobAPI.model.dto.CompanyUpdateDTO;
import com.danluan.seuJobAPI.repository.ApplicationRepository;
import com.danluan.seuJobAPI.repository.CompanyRepository;
import com.danluan.seuJobAPI.repository.UserRepository;
import com.danluan.seuJobAPI.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    @Qualifier("jobServiceImpl")
    private JobService jobService;

    @Autowired
    @Qualifier("workerServiceImpl")
    private WorkerService workerService;

    @Autowired
    @Qualifier("companyServiceImpl")
    private CompanyService companyService;

    @Autowired
    @Qualifier("serviceServiceImpl")
    private ServiceService serviceService;

    @Override
    public List<ApplicationDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream().map(this::toDTO).toList();
    }

    @Override
    public ApplicationDTO getApplicationById(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found for ID: " + id));
        return toDTO(application);
    }

    @Override
    public ApplicationDTO createApplication(ApplicationDTO applicationDTO) {
        applicationRepository.save(toEntity(applicationDTO));
        return applicationDTO;
    }

    @Override
    public ApplicationDTO updateApplication(ApplicationDTO applicationDTO) {
        applicationRepository.save(toEntity(applicationDTO));
        return applicationDTO;
    }

    @Override
    public void deleteApplication(Integer id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public ApplicationDTO toDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setJob(jobService.getJobById(application.getJob().getId()));
        applicationDTO.setWorker(workerService.getWorkerDTOById(application.getJob().getId()));
        applicationDTO.setService(serviceService.getServiceById(application.getService().getId()));
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setDateApply(application.getApplyDate());
        return applicationDTO;
    }

    @Override
    public Application toEntity(ApplicationDTO applicationDTO) {
        Application application = new Application();
        if (applicationDTO.getId() != 0)
            application.setId(applicationDTO.getId());
        application.setJob(jobService.toEntity(applicationDTO.getJob()));
        application.setWorker(workerService.toEntity(applicationDTO.getWorker()));
        application.setStatus(applicationDTO.getStatus());
        application.setService(serviceService.toEntity(applicationDTO.getService()));
        application.setApplyDate(applicationDTO.getDateApply());
        return application;
    }
}
