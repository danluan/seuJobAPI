package com.danluan.seuJobAPI.repository;

import com.danluan.seuJobAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
