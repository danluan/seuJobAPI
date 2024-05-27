package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.exception.UserIdAlreadyInUseException;
import com.danluan.seuJobAPI.exception.UserNotFoundException;
import com.danluan.seuJobAPI.exception.WorkerIdAlreadyInUseException;
import com.danluan.seuJobAPI.exception.WorkerNotFoundException;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;
import com.danluan.seuJobAPI.service.WorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/worker")
@RestController
@RequiredArgsConstructor
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<WorkerDTO> getWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorkerById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(workerService.getWorkerDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<WorkerDTO> getWorkerByUserId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(workerService.getWorkerDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WorkerDTO> createWorker(@RequestBody @Valid WorkerDTO workerDTO) {
        try {
            WorkerDTO createdWorker = workerService.createWorker(workerDTO);
            return new ResponseEntity<>(createdWorker, HttpStatus.CREATED);
        } catch (UserIdAlreadyInUseException | WorkerIdAlreadyInUseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WorkerDTO> updateWorker(@RequestBody @Valid WorkerDTO workerDTO) {
        try {
            WorkerDTO workerUpdated = workerService.updateWorker(workerDTO);
            return new ResponseEntity<>(workerUpdated, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Integer id) {
        try {
            workerService.deleteWorker(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (WorkerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
