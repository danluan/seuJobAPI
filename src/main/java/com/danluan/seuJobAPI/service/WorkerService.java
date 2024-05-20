package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;

import java.util.List;

public interface WorkerService {
    WorkerDTO toDTO(Worker worker);
    List<WorkerDTO> getAllWorkers();
    WorkerDTO getWorkerDTOById(Integer id);
    Worker getWorkerById(Integer id);
    WorkerDTO createWorker(WorkerDTO workerDTO);
    WorkerDTO updateWorker(WorkerDTO workerDTO);
    void deleteWorker(Integer id);
    void updateWorkerResume(Integer id, Resume resume);
}
