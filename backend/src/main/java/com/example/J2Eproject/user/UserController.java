package com.example.J2Eproject.user;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable("id") ObjectId id) {
//        return repository.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public void updateUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
//        user.setId(id);
//        repository.save(user);
//    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        //user.setId(ObjectId.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        repository.save(user);
        return user;
    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable ObjectId id) {
//        repository.delete(repository.findById(id));
//    }
}