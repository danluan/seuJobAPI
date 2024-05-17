package com.danluan.seuJobAPI.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String login;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 11)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Worker> workers = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Freelancer> freelancers = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Admin> admins = new ArrayList<>();

    public User(String name, String login, String email, String password, String phoneNumber) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return !admins.isEmpty();
    }

    public List<String> getRolesByUser(){
        List<String> roles = new ArrayList<>();
        if(!companies.isEmpty()){
            roles.add("COMPANY");
        }
        if(!workers.isEmpty()){
            roles.add("WORKER");
        }
        if(!freelancers.isEmpty()){
            roles.add("FREELANCER");
        }
        if(!admins.isEmpty()){
            roles.add("ADMIN");
        }
        return roles;
    }
}
