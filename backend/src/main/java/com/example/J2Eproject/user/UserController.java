package com.example.J2Eproject.user;

import com.example.J2Eproject.security.JWTAuthenticationFilter;
import com.example.J2Eproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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

//
//    @PutMapping("/{id}")
//    public void updateUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
//        user.setId(id);
//        repository.save(user);
//    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        //user.setId(ObjectId.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));

        var u = service.add(user);
        return toDto(u);
    }

    @GetMapping("/profile")
    @ResponseBody
    public String currentUserDetails(Principal principal) {
        return principal.getName();
    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable ObjectId id) {
//        repository.delete(repository.findById(id));
//    }
}
