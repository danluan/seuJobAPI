package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Freelancer;
import com.danluan.seuJobAPI.model.Job;
import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.model.dto.FreelancerUpdateDTO;
import com.danluan.seuJobAPI.model.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJobs();
    JobDTO getJobById(Integer id);
    JobDTO save(JobDTO jobDTO);
    JobDTO updateJob(JobDTO jobDTO);
    void deleteFreelancer(Integer id);
    JobDTO toDTO(Job job);
    Job toEntity(JobDTO jobDTO);
}
