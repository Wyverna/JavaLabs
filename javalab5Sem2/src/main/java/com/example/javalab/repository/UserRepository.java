package com.example.javalab.repository;

import com.example.javalab.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    List<User> findByLoginAndPassword(String Login,String Password);
}
