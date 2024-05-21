package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.model.Service;
import com.danluan.seuJobAPI.model.dto.ServiceDTO;
import com.danluan.seuJobAPI.model.dto.ServiceUpdateDTO;
import com.danluan.seuJobAPI.repository.ServiceRepository;
import com.danluan.seuJobAPI.service.ServiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(this::toDto).toList();
    }

    @Override
    public ServiceDTO getServiceById(Integer id) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found for ID: " + id));
        return toDto(service);
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = this.toEntity(serviceDTO);
        return this.toDto(serviceRepository.save(service));
    }

    @Override
    public ServiceDTO updateService(Integer id, ServiceUpdateDTO serviceDTO) {
        Service service = serviceRepository.findById(id).orElse(null);

        if (service == null) {
            return null;
        }

        service.setTitle(serviceDTO.getTitle());
        service.setDescription(serviceDTO.getDescription());
        service.setLocation(serviceDTO.getLocation());
        service.setRemuneration(serviceDTO.getRemuneration());
        service.setEndDate(serviceDTO.getEndDate());

        return this.toDto(serviceRepository.save(service));
    }

    @Override
    public void deleteServiceById(Integer id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceDTO toDto(Service service) {
        return new ServiceDTO(service);
    }

    @Override
    public Service toEntity(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setId(serviceDTO.getId());
        service.setLocation(serviceDTO.getLocation());
        service.setTitle(serviceDTO.getTitle());
        service.setDescription(serviceDTO.getDescription());
        service.setRemuneration(serviceDTO.getRemuneration());
        service.setEndDate(serviceDTO.getEndDate());
        return service;
    }
}
