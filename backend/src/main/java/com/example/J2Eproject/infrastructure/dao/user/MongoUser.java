package com.example.J2Eproject.infrastructure.dao.user;

import com.example.J2Eproject.infrastructure.dao.gif.MongoGif;
import com.example.J2Eproject.infrastructure.persistence.entities.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class MongoUser {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef
    private Set<MongoGif> mongoGifs = new HashSet<>();

    public MongoUser() {
    }

    public MongoUser(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username,
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addGif(MongoGif mongoGif) {
        this.mongoGifs.add(mongoGif);
    }

    public void removeGif(MongoGif mongoGif) {
        this.mongoGifs.removeIf(g -> g.get_id().equals(mongoGif.get_id()));
    }

    public Set<MongoGif> getMongoGifs() {
        return mongoGifs;
    }
}
