package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.FreelancerNotFoundException;
import com.danluan.seuJobAPI.exception.InvalidFreelancerException;
import com.danluan.seuJobAPI.exception.UserIdAlreadyInUseException;
import com.danluan.seuJobAPI.exception.UserNotFoundException;
import com.danluan.seuJobAPI.model.dto.FreelancerDTO;
import com.danluan.seuJobAPI.service.FreelancerService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancer")
@NoArgsConstructor
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    @GetMapping("/{id}")
    public ResponseEntity<FreelancerDTO> getFreelancerById(@PathVariable Integer id) {
        try {
            FreelancerDTO freelancerDTO = freelancerService.getFreelancerById(id);
            return new ResponseEntity<>(freelancerDTO, HttpStatus.OK);
        } catch (FreelancerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<FreelancerDTO>> getFreelancers() {
        try {
            List<FreelancerDTO> freelancers = freelancerService.getAllFreelancers();
            return new ResponseEntity<>(freelancers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<FreelancerDTO> createFreelancer(@RequestBody @Valid FreelancerDTO freelancerDTO) {
        try {
            FreelancerDTO createdFreelancer = freelancerService.createFreelancer(freelancerDTO);
            return new ResponseEntity<>(createdFreelancer, HttpStatus.CREATED);
        } catch (InvalidFreelancerException | UserIdAlreadyInUseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Integer id) {
        try {
            freelancerService.deleteFreelancer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FreelancerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
