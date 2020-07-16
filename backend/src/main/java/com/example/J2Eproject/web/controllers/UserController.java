package com.example.J2Eproject.web.controllers;

import com.example.J2Eproject.infrastructure.persistence.entities.User;
import com.example.J2Eproject.web.controllers.post.UserDTO;
import com.example.J2Eproject.use_case.services.authent.UserDetailsImpl;
import com.example.J2Eproject.infrastructure.persistence.dal.UserRepository;
import com.example.J2Eproject.use_case.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.service = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
        return service.getById(id)
                .map(this::toDto)
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity<UserDTO> toDto(User user) {
        var body = new UserDTO();
        body.setUsername(user.getUsername());
        body.setEmail(user.getEmail());
        body.setFirstName(user.getFirstName());
        body.setLastName(user.getLastName());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/profile")
    public User getUserDetails() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        return service.getById(userId)
                .orElseThrow(() -> new RuntimeException("Error. User not found with id: " + userId));
    }


    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable ObjectId userId, @RequestBody UserDTO user) {
        return userRepository.findById(userId).map(userFound -> {
            if (user.getEmail() != null) userFound.setEmail(user.getEmail());
            if (user.getUsername() != null) userFound.setUsername(user.getUsername());
            if (user.getFirstName() != null) userFound.setFirstName(user.getFirstName());
            if (user.getLastName() != null) userFound.setLastName(user.getLastName());
            if (user.getPassword() != null) userFound.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(userFound);
        }).orElseThrow(() -> new RuntimeException("Error. User not found with id: " + userId));
    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable ObjectId id) {
//        repository.delete(repository.findById(id));
//    }
}
