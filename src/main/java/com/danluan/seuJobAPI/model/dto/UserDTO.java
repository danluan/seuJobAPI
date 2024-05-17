package com.danluan.seuJobAPI.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String login;
    private String password;
    private String phone;
    private List<String> roles;

    public UserDTO(String name, String email, String login, String password, String phone, List<String> roles) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
    }
}
