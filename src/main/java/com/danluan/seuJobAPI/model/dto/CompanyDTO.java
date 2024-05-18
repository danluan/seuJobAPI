package com.danluan.seuJobAPI.model.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Integer id;
    private Integer userId;
    private String cnpj;
    private String name;
    private String email;
    private String login;
    private String phone;
    private List<JobDTO> jobs;

    public CompanyDTO(Integer userId) {
        this.userId = userId;
    }
}
