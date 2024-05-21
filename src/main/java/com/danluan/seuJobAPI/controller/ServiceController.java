package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.model.dto.ServiceDTO;
import com.danluan.seuJobAPI.model.dto.ServiceUpdateDTO;
import com.danluan.seuJobAPI.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<ServiceDTO> getServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable Integer id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.createService(serviceDTO);
    }

    @PutMapping("/{id}")
    public ServiceDTO updateService(@PathVariable Integer id, @RequestBody ServiceUpdateDTO serviceDTO) {
        return serviceService.updateService(id, serviceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Integer id) {
        serviceService.deleteServiceById(id);
    }
}
