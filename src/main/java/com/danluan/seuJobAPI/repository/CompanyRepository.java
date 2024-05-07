package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
