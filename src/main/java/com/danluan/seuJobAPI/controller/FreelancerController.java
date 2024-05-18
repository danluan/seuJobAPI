package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.service.FreelancerService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancer")
@NoArgsConstructor
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    @GetMapping("/{id}")
    public FreelancerDTO getFreelancerById(@PathVariable Integer id) {
        return freelancerService.getFreelancerById(id);
    }

    @GetMapping
    public List<FreelancerDTO> getFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    @PostMapping
    public FreelancerDTO createFreelancer(@RequestBody FreelancerDTO freelancerDTO) {
        return freelancerService.createFreelancer(freelancerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFreelancer(@PathVariable Integer id) {
        freelancerService.deleteFreelancer(id);
    }

}
