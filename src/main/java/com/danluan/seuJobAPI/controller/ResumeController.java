package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.WorkerIdAlreadyInUse;
import com.danluan.seuJobAPI.model.dto.ResumeDTO;
import com.danluan.seuJobAPI.model.dto.ResumeUpdateDTO;
import com.danluan.seuJobAPI.service.ResumeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Integer id) {
        try {
            ResumeDTO resume = resumeService.getResumeById(id);
            return new ResponseEntity<>(resume, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResumeDTO> createResume(@RequestBody ResumeDTO resumeDTO) {
        try {
            ResumeDTO createdResume = resumeService.createResume(resumeDTO);
            return new ResponseEntity<>(createdResume, HttpStatus.CREATED);
        } catch (WorkerIdAlreadyInUse e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumeDTO> updateResume(@PathVariable Integer id, @RequestBody ResumeUpdateDTO resumeDTO) {
        try {
            ResumeDTO updatedResume = resumeService.updateResume(id, resumeDTO);
            return new ResponseEntity<>(updatedResume, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        try {
            resumeService.deleteResume(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
