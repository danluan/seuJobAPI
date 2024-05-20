package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Resume;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUpdateDTO {
    private Integer id;
    private String education;
    private String experience;
    private String skills;

    public ResumeUpdateDTO(Resume resume) {
        this.id = resume.getId();
        this.education = resume.getEducation();
        this.experience = resume.getExperiences();
        this.skills = resume.getSkills();
    }

    public ResumeUpdateDTO(String experiences, String education, String skills) {
        this.experience = experiences;
        this.education = education;
        this.skills = skills;
    }
}
