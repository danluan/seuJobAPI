package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Resume;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
public class ResumeUpdateDTO {
//    private Integer id;
    @NotEmpty(message = "Education can not be empty")
    private String education;

    @NotEmpty(message = "Experience can not be empty")
    private String experience;

    @NotEmpty(message = "Skills can not be empty")
    private String skills;

    public ResumeUpdateDTO(Resume resume) {
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
