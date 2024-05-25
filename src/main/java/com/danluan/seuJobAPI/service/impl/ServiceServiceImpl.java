package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.ServiceNotFoundException;
import com.danluan.seuJobAPI.model.Freelancer;
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
    @Autowired
    private FreelancerServiceImpl freelancerServiceImpl;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(this::toDto).toList();
    }

    @Override
    public ServiceDTO getServiceById(Integer id) {
        Service service = serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
        return toDto(service);
    }

    @Override
    public Service getServiceEntityById(Integer id) {
        return serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = toEntity(serviceDTO);
        Freelancer freelancer = freelancerServiceImpl.getFreelancerEntityById(serviceDTO.getFreelancerId());
        service.setFreelancer(freelancer);
        return toDto(serviceRepository.save(service));
    }

    @Override
    public ServiceDTO updateService(Integer id, ServiceUpdateDTO serviceDTO) {
        Service service = serviceRepository.findById(id).orElse(null);

        if (service == null) {
            throw new ServiceNotFoundException();
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
        serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceDTO toDto(Service service) {
        return new ServiceDTO(service);
    }

    @Override
    public Service toEntity(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setFreelancer(freelancerServiceImpl.getFreelancerEntityById(serviceDTO.getFreelancerId()));
        service.setId(serviceDTO.getId());
        service.setLocation(serviceDTO.getLocation());
        service.setTitle(serviceDTO.getTitle());
        service.setDescription(serviceDTO.getDescription());
        service.setRemuneration(serviceDTO.getRemuneration());
        service.setEndDate(serviceDTO.getEndDate());
        return service;
    }
}
