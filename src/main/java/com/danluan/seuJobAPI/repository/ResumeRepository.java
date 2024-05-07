package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}
