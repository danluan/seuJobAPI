package com.danluan.seuJobAPI.model.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDTO {
    private Integer id;
    private String cnpj;
    private String name;
    private String email;
    private String login;
    private String phone;
}
