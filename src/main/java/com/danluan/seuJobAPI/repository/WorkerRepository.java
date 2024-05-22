package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Worker;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    @Query("SELECT w FROM Worker w WHERE w.user.id = :userId")
    Optional<Worker> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT w FROM Worker w INNER JOIN Application a ON a.worker = w where w.user.id = :userId")
    Optional<Worker> findWorkerAndApplicationByUserId(@Param("userId") Integer userId);

}
