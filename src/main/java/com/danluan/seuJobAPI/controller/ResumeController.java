package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.model.dto.ResumeDTO;
import com.danluan.seuJobAPI.model.dto.ResumeUpdateDTO;
import com.danluan.seuJobAPI.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public List<ResumeDTO> getResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResumeDTO getResumeById(@PathVariable Integer id) {
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public ResumeDTO createResume(@RequestBody ResumeDTO resumeDTO) {
        return resumeService.createResume(resumeDTO);
    }

    @PutMapping("/{id}")
    public ResumeDTO updateResume(@PathVariable Integer id, @RequestBody ResumeUpdateDTO resumeDTO) {
        return resumeService.updateResume(id, resumeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteResume(@PathVariable Integer id) {
        resumeService.deleteResume(id);
    }
}
