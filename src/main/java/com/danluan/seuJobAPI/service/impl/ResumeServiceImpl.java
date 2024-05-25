package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.WorkerIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.ResumeDTO;
import com.danluan.seuJobAPI.model.dto.ResumeUpdateDTO;
import com.danluan.seuJobAPI.repository.ResumeRepository;
import com.danluan.seuJobAPI.service.ResumeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private WorkerServiceImpl workerService;

    @Override
    public List<ResumeDTO> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream().map(this::toDto).toList();
    }

    @Override
    public ResumeDTO getResumeById(Integer id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resume not found for ID: " + id));
        return toDto(resume);
    }

    @Override
    public ResumeDTO createResume(ResumeDTO resumeDTO) {
        if (resumeRepository.findByWorkerId(resumeDTO.getWorkerId()).isPresent()) {
            throw new WorkerIdAlreadyInUse();
        }
        Resume resume = this.toEntity(resumeDTO);
        Worker worker = workerService.getWorkerById(resumeDTO.getWorkerId());
        resume.setWorker(worker);
        worker.setResume(resume);
        workerService.updateWorker(workerService.toDTO(worker));

        resumeRepository.save(resume);
        return this.toDto(resume);
    }

    @Override
    public ResumeDTO updateResume(Integer id, ResumeUpdateDTO resumeDTO) {
        Resume resume = resumeRepository.findById(id).orElse(null);
        if (resume == null) {
            throw new EntityNotFoundException("Resume not found for ID: " + id);
        }

        resume.setSkills(resumeDTO.getSkills());
        resume.setExperiences(resumeDTO.getExperience());
        resume.setEducation(resumeDTO.getEducation());

        return this.toDto(resumeRepository.save(resume));
    }

    @Override
    public void deleteResume(Integer id) {
        try {
            Resume resume = resumeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resume not found for ID: " + id));
            resumeRepository.delete(resume);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResumeDTO toDto(Resume resume) {
        ResumeDTO resumeDTO = new ResumeDTO(resume);
        resumeDTO.setWorkerId(resume.getWorker().getId());
        return resumeDTO;
    }

    @Override
    public Resume toEntity(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setId(resumeDTO.getId());
        resume.setEducation(resumeDTO.getEducation());
        resume.setExperiences(resumeDTO.getExperience());
        resume.setSkills(resumeDTO.getSkills());
        Worker worker = workerService.getWorkerById(resumeDTO.getWorkerId());
        resume.setWorker(worker);
        return resume;
    }
}
