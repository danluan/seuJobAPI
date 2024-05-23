package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.enums.ApplicationStatus;
import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.ApplicationCreateDTO;
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
    public ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        Application application = new Application();
        if(applicationCreateDTO.getJobId() != null) {
            application.setJob(jobService.getJobEntityById(applicationCreateDTO.getJobId()));
            application.setWorker(workerService.getWorkerById(applicationCreateDTO.getWorkerId()));
        } else if (applicationCreateDTO.getServiceId() != null) {
            application.setService(serviceService.getServiceEntityById(applicationCreateDTO.getServiceId()));
            application.setWorker(workerService.getWorkerById(applicationCreateDTO.getWorkerId()));
        } else {
            throw new EntityNotFoundException("Job or Service not found for ID: " + applicationCreateDTO.getJobId());
        }
        application.setStatus(ApplicationStatus.P);
        return toDTO(applicationRepository.save(application));
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
        if(application.getJob() != null)
            applicationDTO.setJobId(application.getJob().getId());

        if(application.getService() != null)
            applicationDTO.setServiceId(application.getService().getId());
        applicationDTO.setWorkerId(application.getWorker().getId());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setDateApply(application.getApplyDate());
        return applicationDTO;
    }

    @Override
    public Application toEntity(ApplicationDTO applicationDTO) {
        Application application = new Application();
        if (applicationDTO.getId() != 0)
            application.setId(applicationDTO.getId());
        application.setJob(jobService.getJobEntityById(applicationDTO.getJobId()));
        application.setWorker(workerService.getWorkerById(applicationDTO.getWorkerId()));
        application.setStatus(applicationDTO.getStatus());
        application.setService(serviceService.getServiceEntityById(applicationDTO.getServiceId()));
        application.setApplyDate(applicationDTO.getDateApply());
        return application;
    }
}
