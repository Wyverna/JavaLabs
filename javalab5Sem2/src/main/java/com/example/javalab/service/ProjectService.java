package com.example.javalab.service;

import com.example.javalab.entity.Project;
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

    public void DeleteProject(long id) {
        cRepository.deleteById(id);
    }

    public void UpdateProject(Project project) {
        cRepository.save(project);
    }

    public Set<Project> findProjectsByWorker(long id) {
        return cRepository.findByWorker_id(id);
    }

    public Optional<Project> getById(long id) {
        return cRepository.findById(id);
    }
}
