package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Resume;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class ResumeDTO {
    private Integer id;
    private String education;
    private String experience;
    private String skills;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateDate;

    public ResumeDTO() {
    }

    public ResumeDTO(Resume resume) {
        this.id = resume.getId();
        this.education = resume.getEducation();
        this.experience = resume.getExperiences();
        this.skills = resume.getSkills();
        this.creationDate = resume.getCreationDate();
    }

    public ResumeDTO(Integer id, String education, String experience, String skills, Date creationDate, Date updateDate) {
        this.id = id;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }
}
