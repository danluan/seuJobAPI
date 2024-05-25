package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.JobNotFoundException;
import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Job;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.model.dto.JobDTO;
import com.danluan.seuJobAPI.repository.JobRepository;
import com.danluan.seuJobAPI.service.ApplicationService;
import com.danluan.seuJobAPI.service.CompanyService;
import com.danluan.seuJobAPI.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyService companyService;

    @Lazy
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public JobDTO getJobById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return toDTO(job.get());
        } else {
            throw new JobNotFoundException();
        }
    }

    @Override
    public Job getJobEntityById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return job.get();
        } else {
            throw new JobNotFoundException();
        }
    }

    @Override
    public JobDTO save(JobDTO jobDTO) {
        Job job = toEntity(jobDTO);
        job.setCompany(companyService.getCompanyEntityById(jobDTO.getCompanyId()));
        jobRepository.save(job);
        return toDTO(job);
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(JobNotFoundException::new);
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(Float.parseFloat(jobDTO.getSalary()));
        job.setContractType(jobDTO.getContract_type());
        jobRepository.save(job);
        return toDTO(job);
    }

    @Override
    public void deleteJob(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.delete(job.get());
        } else {
            throw new JobNotFoundException();
        }
    }

    @Override
    public List<ApplicationDTO> getApplicationsByJob(Integer id) {
        List<Application> applications = jobRepository.getApplicationsByJob(id);

        return applications.stream().map(applicationService::toDTO).collect(Collectors.toList());
    }

    @Override
    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompanyId(job.getCompany() != null ? job.getCompany().getId() : null);
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setPublish_date(job.getPublishDate().toString());
        jobDTO.setSalary(job.getSalary().toString());
        jobDTO.setContract_type(job.getContractType());
        return jobDTO;
    }

    @Override
    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(Float.parseFloat(jobDTO.getSalary()));
        job.setContractType(jobDTO.getContract_type());
        return job;
    }


}
