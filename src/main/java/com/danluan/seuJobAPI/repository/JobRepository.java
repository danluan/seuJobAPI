package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
