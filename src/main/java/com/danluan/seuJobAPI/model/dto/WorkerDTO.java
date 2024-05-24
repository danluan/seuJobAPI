package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private Integer id;
    private Integer userId;
    private String name;
    private String email;
    private String login;
    private String phone;
    private ResumeUpdateDTO resume;
    private List<ApplicationDTO> applications;

    public WorkerDTO(Integer userId) {
        this.id = userId;
    }
}
