package com.example.javalab.repository;

import com.example.javalab.entity.Project;
import com.example.javalab.entity.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
   List<Project> findAllByWorker_Id(long id);
}
