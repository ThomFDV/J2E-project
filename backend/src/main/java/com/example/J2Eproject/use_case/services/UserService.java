package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.infrastructure.persistence.dal.UserRepository;
import com.example.J2Eproject.infrastructure.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User add(User user) {
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(String id) {
        return repository.findById(id);
    }

    public User save(User user) { return repository.save(user); }
}
