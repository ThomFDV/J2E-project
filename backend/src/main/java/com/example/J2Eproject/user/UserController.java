package com.example.J2Eproject.user;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity getUserDetails() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userDetails);
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
