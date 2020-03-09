package com.example.javalab.repository;

import com.example.javalab.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    Set<Project> findByWorker_id(long worker_id);
}
