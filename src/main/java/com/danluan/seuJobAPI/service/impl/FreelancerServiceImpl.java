package com.danluan.seuJobAPI.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.danluan.seuJobAPI.exception.FreelancerNotFoundException;
import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.exception.UserNotFoundException;
import com.danluan.seuJobAPI.model.Freelancer;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.model.dto.FreelancerUpdateDTO;
import com.danluan.seuJobAPI.repository.FreelancerRepository;
import com.danluan.seuJobAPI.service.FreelancerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerServiceImpl implements FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<FreelancerDTO> getAllFreelancers() {
        List<Freelancer> freelancers = freelancerRepository.findAll();
        return freelancers.stream().map(this::toDTO).toList();
    }

    @Override
    public FreelancerDTO getFreelancerById(Integer id) {
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(FreelancerNotFoundException::new);
        return toDTO(freelancer);
    }

    @Override
    public FreelancerDTO createFreelancer(FreelancerDTO freelancerDTO) {
        if (freelancerRepository.findByUserId(freelancerDTO.getUserId()).isPresent()) {
            throw new UserIdAlreadyInUse();
        }

        User user = userService.getUserById(freelancerDTO.getUserId());

        if (user == null) {
            throw new UserNotFoundException();
        }
        Freelancer freelancer = new Freelancer(user);

        return this.toDTO(freelancerRepository.save(freelancer));
    }

    @Override
    public FreelancerDTO updateFreelancer(FreelancerUpdateDTO freelancerUpdateDTO) {
        return null;
    }

    @Override
    public void deleteFreelancer(Integer id) {
        freelancerRepository.findById(id).orElseThrow(FreelancerNotFoundException::new);
        freelancerRepository.deleteById(id);
    }

    @Override
    public FreelancerDTO toDTO(Freelancer freelancer) {
        FreelancerDTO freelancerDTO = new FreelancerDTO();
        freelancerDTO.setId(freelancer.getId());
        freelancerDTO.setUserId(freelancer.getUser().getId());
        freelancerDTO.setName(freelancer.getUser().getName());
        freelancerDTO.setEmail(freelancer.getUser().getEmail());
        freelancerDTO.setLogin(freelancer.getUser().getLogin());
        freelancerDTO.setPhone(freelancer.getUser().getPhoneNumber());
        //TODO: adicionar aqui a lista de services
        return freelancerDTO;
    }

    @Override
    public Freelancer toEntity(FreelancerDTO freelancerDTO) {
        return null;
    }
}
