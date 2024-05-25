package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.ServiceNotFoundException;
import com.danluan.seuJobAPI.model.dto.ServiceDTO;
import com.danluan.seuJobAPI.model.dto.ServiceUpdateDTO;
import com.danluan.seuJobAPI.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getServices() {
        try {
            List<ServiceDTO> services = serviceService.getAllServices();
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Integer id) {
        try {
            ServiceDTO serviceDTO = serviceService.getServiceById(id);
            return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
        } catch (ServiceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody @Valid ServiceDTO serviceDTO) {
        try {
            ServiceDTO createdService = serviceService.createService(serviceDTO);
            return new ResponseEntity<>(createdService, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Integer id, @RequestBody @Valid ServiceUpdateDTO serviceDTO) {
        try {
            ServiceDTO updatedService = serviceService.updateService(id, serviceDTO);
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        } catch (ServiceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        try {
            serviceService.deleteServiceById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
