package com.danluan.seuJobAPI.model.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerUpdateDTO {
    private Integer id;
    private String name;
    private String email;
    private String login;
    private String phone;
}
