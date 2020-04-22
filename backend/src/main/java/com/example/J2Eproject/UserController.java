package com.example.J2Eproject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id")ObjectId id) {
        return repository.findById(id);
    }

    @PutMapping("/user/{id}")
    public void updateUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
        user.setId(id);
        repository.save(user);
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        user.setId(ObjectId.get());
        repository.save(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable ObjectId id) {
        repository.delete(repository.findById(id));
    }
}
