package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.ApplicationNotFoundException;
import com.danluan.seuJobAPI.exception.WorkerAlreadyAppliedException;
import com.danluan.seuJobAPI.model.dto.ApplicationCreateDTO;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/application")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDTO> create(@RequestBody @Valid ApplicationCreateDTO applicationDTO) {
        try {
            ApplicationDTO createdApplication = applicationService.createApplication(applicationDTO);
            return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
        } catch (WorkerAlreadyAppliedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Integer id) {
        try {
            applicationService.deleteApplication(id);
            return new ResponseEntity<>("Application deleted.", HttpStatus.NO_CONTENT);
        } catch (ApplicationNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        try {
            List<ApplicationDTO> applications = applicationService.getAllApplications();
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Integer id) {
        try {
            ApplicationDTO application = applicationService.getApplicationById(id);
            return new ResponseEntity<>(application, HttpStatus.OK);
        } catch (ApplicationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
