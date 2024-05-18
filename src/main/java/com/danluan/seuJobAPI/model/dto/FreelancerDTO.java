package com.danluan.seuJobAPI.model.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerDTO {
    private Integer id;
    private Integer userId;
    private String name;
    private String email;
    private String login;
    private String phone;
    private List<ServiceDTO> services;
}
