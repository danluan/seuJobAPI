package com.danluan.seuJobAPI.service.impl;

import com.danluan.seuJobAPI.exception.UserIdAlreadyInUse;
import com.danluan.seuJobAPI.model.Resume;
import com.danluan.seuJobAPI.model.User;
import com.danluan.seuJobAPI.model.Worker;
import com.danluan.seuJobAPI.model.dto.ResumeUpdateDTO;
import com.danluan.seuJobAPI.model.dto.WorkerDTO;
import com.danluan.seuJobAPI.repository.WorkerRepository;
import com.danluan.seuJobAPI.service.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers.stream().map(this::toDTO).toList();
    }

    @Override
    public Worker getWorkerById(Integer id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found for ID: " + id));
    }

    @Override
    public WorkerDTO getWorkerDTOById(Integer id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found for ID: " + id));
        return toDTO(worker);
    }

    @Override
    public WorkerDTO createWorker(WorkerDTO workerDTO) {
        if(workerRepository.findByUserId(workerDTO.getUserId()).isPresent()) {
            throw new UserIdAlreadyInUse();
        }

        User user = userService.getUserById(workerDTO.getUserId());
        Worker worker = new Worker();

        worker.setUser(user);

        return this.toDTO(workerRepository.save(worker));

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
        Worker user = workerRepository.findById(id).orElse(null);
        user.setResume(resume);
        workerRepository.save(user);
    }

    @Override
    public WorkerDTO toDTO (Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getId());
        workerDTO.setUserId(worker.getUser().getId());
        workerDTO.setName(worker.getUser().getName());
        workerDTO.setEmail(worker.getUser().getEmail());
        workerDTO.setLogin(worker.getUser().getLogin());
        workerDTO.setPhone(worker.getUser().getPhoneNumber());
        workerDTO.setApplications(worker.getApplications());
        workerDTO.setResume(
                worker.getResume() != null ? new ResumeUpdateDTO(worker.getResume()) : null
        );
        return workerDTO;
    }

    public Worker toEntity (WorkerDTO workerDTO) {
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
