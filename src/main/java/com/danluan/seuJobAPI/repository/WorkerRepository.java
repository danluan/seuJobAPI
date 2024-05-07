package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
