package com.danluan.seuJobAPI.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String login;
    private String password;
    private String phone;
}