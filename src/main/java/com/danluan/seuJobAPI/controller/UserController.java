package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.model.dto.UserDTO;
import com.danluan.seuJobAPI.service.UserService;
import com.danluan.seuJobAPI.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserDTOById(id);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        userDTO.setId(id);
        return userService.update(userDTO);
    }
}
