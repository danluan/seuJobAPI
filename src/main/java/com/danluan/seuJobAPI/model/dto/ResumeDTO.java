package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Resume;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {
    private Integer id;

    @NotNull(message = "WorkerId can not be empty")
    private Integer workerId;

    @NotEmpty(message = "Education can not be empty")
    private String education;

    @NotEmpty(message = "Experience can not be empty")
    private String experience;

    @NotEmpty(message = "Skills can not be empty")
    private String skills;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateDate;

    public ResumeDTO(Resume resume) {
        this.id = resume.getId();
        this.education = resume.getEducation();
        this.experience = resume.getExperiences();
        this.skills = resume.getSkills();
        this.creationDate = resume.getCreationDate();
        this.updateDate = resume.getUpdateDate();
    }

    public ResumeDTO(String experiences, String education, String skills) {
        this.experience = experiences;
        this.education = education;
        this.skills = skills;
    }
}
