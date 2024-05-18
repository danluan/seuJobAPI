package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {
    @Query("SELECT f FROM Freelancer f WHERE f.user.id = :userId")
    Optional<Freelancer> findByUserId(@Param("userId") Integer userId);}
