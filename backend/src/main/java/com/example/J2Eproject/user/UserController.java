package com.example.J2Eproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity getUserDetails() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(userDetails);
    }

//
//    @PutMapping("/{id}")
//    public void updateUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
//        user.setId(id);
//        repository.save(user);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable ObjectId id) {
//        repository.delete(repository.findById(id));
//    }
}
