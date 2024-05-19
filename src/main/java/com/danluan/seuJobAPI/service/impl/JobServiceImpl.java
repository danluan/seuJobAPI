package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.JobNotFound;
import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Freelancer;
import com.danluan.seuJobAPI.model.Job;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.model.dto.FreelancerUpdateDTO;
import com.danluan.seuJobAPI.model.dto.JobDTO;
import com.danluan.seuJobAPI.repository.FreelancerRepository;
import com.danluan.seuJobAPI.repository.JobRepository;
import com.danluan.seuJobAPI.service.FreelancerService;
import com.danluan.seuJobAPI.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserServiceImpl userService;

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
            throw new JobNotFound();
        }
    }

    @Override
    public JobDTO save(JobDTO jobDTO) {
        jobRepository.save(toEntity(jobDTO));
        return jobDTO;
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        return null;
    }

    @Override
    public void deleteJob(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.delete(job.get());
        } else {
            throw new JobNotFound();
        }
    }

    @Override
    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
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
        return null;
    }
}
