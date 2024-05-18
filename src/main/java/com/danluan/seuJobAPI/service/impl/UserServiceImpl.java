package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.LoginAlreadyInUse;
import com.danluan.seuJobAPI.model.*;
import com.danluan.seuJobAPI.model.dto.UserDTO;
import com.danluan.seuJobAPI.exception.SenhaInvalidaException;
import com.danluan.seuJobAPI.repository.*;
import com.danluan.seuJobAPI.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        if (existsLogin(userDTO.getLogin())) {
            User user = dtoParaUser(userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userParaDTO(userRepository.save(user));
        } else {
            throw new LoginAlreadyInUse();
        }
    }

    public Boolean existsLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    private void extractRolesByList(UserDTO userDTO, User user) {
        List<String> roles = userDTO.getRoles();

        if(roles.contains("ADMIN")){
            Admin admin = new Admin(user);
            admin.setUser(user);
            adminRepository.save(admin);
        }
        if (roles.contains("WORKER")){
             Worker worker = new Worker(user);
             worker.setUser(user);
             workerRepository.save(worker);
        }
        if (roles.contains("COMPANY")){
            Company company = new Company(user);
            company.setUser(user);
            companyRepository.save(company);
        }
        if (roles.contains("FREELANCER")){
            Freelancer freelancer = new Freelancer(user);
            freelancer.setUser(user);
            freelancerRepository.save(freelancer);
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO getUserDTOById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user == null ? null : userParaDTO(user);
    }

    public UserDetails loadUserByLogin(String login) {
        // Consulta ao repositório para obter o usuário com base no nome de usuário fornecido
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

         String[] roles = user.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

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

    public UserDTO userParaDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhoneNumber());
        userDTO.setRoles(user.getRolesByUser());
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userParaDTO).collect(Collectors.toList());
    }


    public UserDTO update(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhone());

        extractRolesByList(userDTO, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userParaDTO(userRepository.save(user));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
