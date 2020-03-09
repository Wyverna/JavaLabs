package com.example.javalab.service;

import com.example.javalab.entity.User;
import com.example.javalab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository cRepository;
    @Autowired
    public UserService(UserRepository cRepository)
    {
        this.cRepository=cRepository;
    }
    public void SaveUser(User user)
    {
        cRepository.save(user);
    }
    public List<User> findUsers()
    {
        return (List<User>)cRepository.findAll();
    }
    public void DeleteUser(String id)
    {
        cRepository.deleteById(id);
    }
    public void UpdateUser(User user)
    {
        cRepository.save(user);
    }
    public Optional<User> getById(String id) {
        return cRepository.findById(id);
    }
    public List<User> getUser(String id,String password) { return cRepository.findByLoginAndPassword(id,password);}

}
