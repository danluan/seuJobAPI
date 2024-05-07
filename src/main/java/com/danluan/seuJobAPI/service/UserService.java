package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(int id);
}
