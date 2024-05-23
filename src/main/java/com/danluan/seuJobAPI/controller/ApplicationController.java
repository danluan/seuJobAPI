package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.SenhaInvalidaException;
import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.dto.ApplicationCreateDTO;
import com.danluan.seuJobAPI.model.dto.ApplicationDTO;
import com.danluan.seuJobAPI.model.dto.CredenciaisDTO;
import com.danluan.seuJobAPI.model.dto.TokenDTO;
import com.danluan.seuJobAPI.security.JwtService;
import com.danluan.seuJobAPI.service.ApplicationService;
import com.danluan.seuJobAPI.service.impl.UserServiceImpl;
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

@RequestMapping("/api/application")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    @Autowired
    @Qualifier("applicationServiceImpl")
    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationDTO create(@RequestBody ApplicationCreateDTO applicationDTO) {
        return applicationService.createApplication(applicationDTO);
    }

    @DeleteMapping("{id}")
    public String deleteApplication(@PathVariable Integer id) {
        applicationService.deleteApplication(id);
        return "Application deleted.";
    }

    @PutMapping("{id}")
    public String deleteApplication(@RequestBody ApplicationDTO applicationDTO) {
        applicationService.updateApplication(applicationDTO);
        return "Application edited.";
    }

    @GetMapping()
    public ApplicationDTO getAllApplications(@PathVariable Integer id) { return applicationService.getApplicationById(id); }

    @GetMapping("{id}")
    public ApplicationDTO getApplication(@PathVariable Integer id) {
        return applicationService.getApplicationById(id);
    }

}

