package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    @Query("SELECT r FROM Resume r WHERE r.worker.id = :workerId")
    Optional<Resume> findByWorkerId(@Param("workerId") Integer workerId);
}
