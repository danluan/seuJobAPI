package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.JobNotFound;
import com.danluan.seuJobAPI.exception.SenhaInvalidaException;
import com.danluan.seuJobAPI.model.Job;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.CredenciaisDTO;
import com.danluan.seuJobAPI.model.dto.JobDTO;
import com.danluan.seuJobAPI.model.dto.TokenDTO;
import com.danluan.seuJobAPI.model.dto.UserDTO;
import com.danluan.seuJobAPI.security.JwtService;
import com.danluan.seuJobAPI.service.JobService;
import com.danluan.seuJobAPI.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("{id}")
    public JobDTO getJobById(@PathVariable Integer id) {
        return jobService.getJobById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public JobDTO salvar(@RequestBody @Valid JobDTO job) {
        return jobService.save(job);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable Integer id) {
        try {
            jobService.deleteJob(id);
            return "Job deleted";
        } catch (JobNotFound e) {
            return e.getMessage();
        }
    }
}
