package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private Integer id;
    private String name;
    private String email;
    private String login;
    private String phone;
    private ResumeDTO resume;
    private List<Application> applications;

    public WorkerDTO(Integer userId) {
        this.id = userId;
    }
}
