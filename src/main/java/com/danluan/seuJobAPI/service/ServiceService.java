package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Service;
import com.danluan.seuJobAPI.model.dto.ServiceDTO;
import com.danluan.seuJobAPI.model.dto.ServiceUpdateDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> getAllServices();
    ServiceDTO getServiceById(Integer id);
    ServiceDTO createService(ServiceDTO serviceDTO);
    ServiceDTO updateService(Integer id, ServiceUpdateDTO serviceDTO);
    void deleteServiceById(Integer id);
    ServiceDTO toDto(Service service);
    Service toEntity(ServiceDTO serviceDTO);
}
