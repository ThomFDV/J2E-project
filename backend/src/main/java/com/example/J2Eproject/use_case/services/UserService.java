package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.domain.UserRepository;
import com.example.J2Eproject.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User add(User user) {
        return repository.add(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(String id) {
        return repository.getById(id);
    }

    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }
}
