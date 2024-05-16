package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.ResumeDTO;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;
import com.danluan.seuJobAPI.repository.WorkerRepository;
import com.danluan.seuJobAPI.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers.stream().map(this::toDTO).toList();
    }

    @Override
    public WorkerDTO getWorkerById(Integer id) {
        Worker worker = workerRepository.findById(id).orElse(null);
        return worker == null ? null : toDTO(worker);
    }

    @Override
    public WorkerDTO createWorker(WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.getUser().setName(workerDTO.getName());
        worker.getUser().setEmail(workerDTO.getEmail());
        worker.getUser().setLogin(workerDTO.getLogin());
        worker.getUser().setPhoneNumber(workerDTO.getPhone());
        return toDTO(workerRepository.save(worker));
    }

    @Override
    public WorkerDTO updateWorker(WorkerDTO workerDTO) {
        Worker worker = workerRepository.findById(workerDTO.getId()).orElse(null);
        if (worker == null) {
            return null;
        }
        return toDTO(worker);
    }

    @Override
    public void deleteWorker(Integer id) {
        workerRepository.deleteById(id);
    }

    @Override
    public void updateWorkerResume(Integer id, Resume resume) {
        Worker user = workerRepository.findById(id).orElse(null);;
        user.setResume(resume);
        workerRepository.save(user);
    }

    @Override
    public WorkerDTO toDTO (Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getId());
        workerDTO.setName(worker.getUser().getName());
        workerDTO.setEmail(worker.getUser().getEmail());
        workerDTO.setLogin(worker.getUser().getLogin());
        workerDTO.setPhone(worker.getUser().getPhoneNumber());
        workerDTO.setResume(new ResumeDTO(worker.getResume()));

        return workerDTO;
    }

    private static Worker toEntity (WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.setId(workerDTO.getId());
        worker.getUser().setName(workerDTO.getName());
        worker.getUser().setEmail(workerDTO.getEmail());
        worker.getUser().setLogin(workerDTO.getLogin());
        worker.getUser().setPhoneNumber(workerDTO.getPhone());
        worker.setResume(new Resume(workerDTO.getResume().getExperience(), workerDTO.getResume().getEducation(), workerDTO.getResume().getSkills()));

        return worker;
    }
}
