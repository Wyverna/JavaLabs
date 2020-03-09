package com.example.javalab.service;

import com.example.javalab.entity.Project;
import com.example.javalab.entity.Worker;
import com.example.javalab.exceptions.ResourceNotFoundException;
import com.example.javalab.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    final ProjectRepository cRepository;

    @Autowired
    public ProjectService(ProjectRepository cRepository) {
        this.cRepository = cRepository;
    }

    public void SaveProject(Project project) {
        cRepository.save(project);
    }

    public List<Project> findProjects() {
        return (List<Project>) cRepository.findAll();
    }

    public void DeleteProject(Project t) {
        cRepository.delete(t);
    }

    public void UpdateProject(Project project,Long id) {
        project.setId(id);
        cRepository.save(project);
    }

    public List<Project> findProjectsByWorker(Worker worker)throws ResourceNotFoundException{
        return cRepository.findAllByWorker_Id(worker.getId());
    }

    public Project getById(long id) throws ResourceNotFoundException {
        return cRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
