package com.example.J2Eproject.domain.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<Gif> gifs = new HashSet<>();

    public User(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username,
                     @NotBlank @Size(max = 30) String firstName, @NotBlank @Size(max = 30) String lastName,
                     @Size(max = 120) String password) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setMongoRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addGif(Gif gif) {
        this.gifs.add(gif);
    }

    public void removeGif(Gif mongoGif) {
        this.gifs.removeIf(g -> g.get_id().equals(mongoGif.get_id()));
    }

    public Set<Gif> getMongoGifs() {
        return gifs;
    }
}
