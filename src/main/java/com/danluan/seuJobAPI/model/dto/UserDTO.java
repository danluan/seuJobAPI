package com.danluan.seuJobAPI.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Login can not be empty")
    private String login;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotEmpty(message = "Phone can not be empty")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "Invalid phone format")
    private String phone;

    private List<String> roles;

    public UserDTO(String name, String email, String login, String password, String phone) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}
