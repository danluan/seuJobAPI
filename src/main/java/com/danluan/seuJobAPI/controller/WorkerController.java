package com.danluan.seuJobAPI.controller;

import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;
import com.danluan.seuJobAPI.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public WorkerDTO getWorkerById(@PathVariable Integer id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    public WorkerDTO createWorker(@RequestBody WorkerDTO workerDTO) {
        return workerService.createWorker(workerDTO);
    }

    @PutMapping
    public WorkerDTO updateWorker(@RequestBody WorkerDTO workerDTO) {
        return workerService.updateWorker(workerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Integer id) {
        workerService.deleteWorker(id);
    }


}
