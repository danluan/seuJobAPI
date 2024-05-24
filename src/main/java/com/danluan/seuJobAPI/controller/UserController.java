package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.LoginAlreadyInUse;
import com.danluan.seuJobAPI.model.dto.CredenciaisDTO;
import com.danluan.seuJobAPI.model.dto.UserDTO;
import com.danluan.seuJobAPI.service.UserService;
import com.danluan.seuJobAPI.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/getUser")
    public UserDTO getUserById(@RequestBody CredenciaisDTO credenciais) {
        return userService.getUserByLogin(credenciais.getLogin());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO salvar( @RequestBody @Valid UserDTO userDTO ){
        return userService.save(userDTO);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable Integer id) {
        userDTO.setId(id);
        return userService.update(userDTO);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
