package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.dto.UserDTO;
import com.danluan.seuJobAPI.exception.SenhaInvalidaException;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.repository.AdminRepository;
import com.danluan.seuJobAPI.repository.UserRepository;
import com.danluan.seuJobAPI.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(UserDTO userDTO) {
        User user = dtoParaUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDetails loadUserByLogin(String login) {
        // Consulta ao repositório para obter o usuário com base no nome de usuário fornecido
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        //String[] roles = user.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };
        String[] roles = { "ADMIN" };

        // Cria e retorna o objeto UserDetails com os detalhes do usuário
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();

    }

    public UserDetails autenticar( User user ){
        UserDetails userDetails = loadUserByLogin(user.getLogin());
        boolean senhasBatem = passwordEncoder.matches( user.getPassword(), userDetails.getPassword());

        if(senhasBatem){
            return userDetails;
        }

        throw new SenhaInvalidaException();
    }

    public User dtoParaUser(UserDTO userDTO){
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhone());

        return user;
    }

}
