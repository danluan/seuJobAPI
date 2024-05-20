package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.dto.ResumeDTO;
import com.danluan.seuJobAPI.model.dto.ResumeUpdateDTO;

import java.util.List;

public interface ResumeService {
    List<ResumeDTO> getAllResumes();
    ResumeDTO getResumeById(Integer id);
    ResumeDTO createResume(ResumeDTO resumeDTO);
    ResumeDTO updateResume(Integer id, ResumeUpdateDTO resumeDTO);
    void deleteResume(Integer id);
    ResumeDTO toDto(Resume resume);
    Resume toEntity(ResumeDTO resumeDTO);
}
