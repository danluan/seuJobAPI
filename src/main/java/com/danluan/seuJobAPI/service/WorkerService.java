package com.danluan.seuJobAPI.service;

import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;

import java.util.List;

public interface WorkerService {
    public WorkerDTO toDTO(Worker worker);
    public List<WorkerDTO> getAllWorkers();
    public WorkerDTO getWorkerById(Integer id);
    public WorkerDTO createWorker(WorkerDTO workerDTO);
    public WorkerDTO updateWorker(WorkerDTO workerDTO);
    public void deleteWorker(Integer id);
    public void updateWorkerResume(Integer id, Resume resume);
}
