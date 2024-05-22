package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Freelancer;
import com.danluan.seuJobAPI.model.Job;
import com.danluan.seuJobAPI.model.dto.*;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJobs();
    JobDTO getJobById(Integer id);
    JobDTO save(JobDTO jobDTO);
    JobDTO updateJob(JobDTO jobDTO);
    void deleteJob(Integer id);
    JobDTO toDTO(Job job);
    Job toEntity(JobDTO jobDTO);
    List<ApplicationDTO> getApplicationsByJob(Integer id);
}
