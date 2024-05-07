package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
