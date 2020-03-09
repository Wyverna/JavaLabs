package com.example.javalab.repository;

import com.example.javalab.entity.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker,Long> {
}
