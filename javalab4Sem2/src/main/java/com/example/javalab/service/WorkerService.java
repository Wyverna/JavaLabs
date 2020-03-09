package com.example.javalab.service;
import com.example.javalab.entity.Worker;
import com.example.javalab.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    final WorkerRepository cRepository;
    @Autowired
    public WorkerService(WorkerRepository cRepository)
    {
        this.cRepository=cRepository;
    }
    public void SaveWorker(Worker worker)
    {
        cRepository.save(worker);
    }
    public List<Worker> findWorkers()
    {
       return (List<Worker>)cRepository.findAll();
    }
    public void DeleteWorker(long id)
    {
        cRepository.deleteById(id);
    }
    public void UpdateWorker(Worker worker)
    {
      cRepository.save(worker);
    }
    public Optional<Worker> getById(long id) {
        return cRepository.findById(id);
    }
}
