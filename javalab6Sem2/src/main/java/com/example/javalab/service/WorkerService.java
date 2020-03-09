package com.example.javalab.service;
import com.example.javalab.entity.Worker;
import com.example.javalab.exceptions.ResourceNotFoundException;
import com.example.javalab.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Column;
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
    public void DeleteWorker(Worker worker)
    {
        cRepository.delete(worker);
    }
    public void UpdateWorker(Worker worker,Long id)
    {
      worker.setId(id);
      cRepository.save(worker);
    }
    public Worker getById(long id) throws ResourceNotFoundException {
        return cRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
