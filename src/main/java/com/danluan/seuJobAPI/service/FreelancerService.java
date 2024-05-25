package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Freelancer;
import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.model.dto.FreelancerUpdateDTO;

import java.util.List;

public interface FreelancerService {
    List<FreelancerDTO> getAllFreelancers();
    FreelancerDTO getFreelancerById(Integer id);
    Freelancer getFreelancerEntityById(Integer freelancerId);
    FreelancerDTO createFreelancer(FreelancerDTO freelancerDTO);
    FreelancerDTO updateFreelancer(FreelancerUpdateDTO freelancerUpdateDTO);
    void deleteFreelancer(Integer id);
    FreelancerDTO toDTO(Freelancer freelancer);
    Freelancer toEntity(FreelancerDTO freelancerDTO);

}
