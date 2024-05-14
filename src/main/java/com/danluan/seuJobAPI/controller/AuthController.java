package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.dto.CredenciaisDTO;
import com.danluan.seuJobAPI.dto.TokenDTO;
import com.danluan.seuJobAPI.dto.UserDTO;
import com.danluan.seuJobAPI.exception.SenhaInvalidaException;
import com.danluan.seuJobAPI.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.danluan.seuJobAPI.security.JwtService;
import com.danluan.seuJobAPI.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User salvar( @RequestBody @Valid UserDTO userDTO ){
        return userService.save(userDTO);
    }

    @PostMapping
    public TokenDTO auth(@RequestBody CredenciaisDTO credenciais) {
        try{
            User usuario = User.builder()
                    .login(credenciais.getLogin())
                    .password(credenciais.getPassword()).build();
            UserDetails usuarioAutenticado = userService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
