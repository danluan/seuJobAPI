package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
