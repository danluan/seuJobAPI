package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.JobNotFoundException;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.model.dto.JobDTO;
import com.danluan.seuJobAPI.security.JwtService;
import com.danluan.seuJobAPI.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/job")
@RestController
@RequiredArgsConstructor
public class JobController {

    @Autowired
    @Qualifier("jobServiceImpl")
    private final JobService jobService;

    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        try {
            List<JobDTO> jobs = jobService.getAllJobs();
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Integer id) {
        try {
            JobDTO job = jobService.getJobById(id);
            return new ResponseEntity<>(job, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<JobDTO> salvar(@RequestBody @Valid JobDTO job) {
        try {
            JobDTO createdJob = jobService.save(job);
            return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<JobDTO> update(@PathVariable Integer id, @RequestBody @Valid JobDTO job) {
        try {
            job.setId(id);
            JobDTO updatedJob = jobService.updateJob(job);
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            jobService.deleteJob(id);
            return new ResponseEntity<>("Job deleted", HttpStatus.NO_CONTENT);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("applications/{id}")
    public ResponseEntity<List<ApplicationDTO>> getApplications(@PathVariable Integer id) {
        try {
            List<ApplicationDTO> applications = jobService.getApplicationsByJob(id);
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
