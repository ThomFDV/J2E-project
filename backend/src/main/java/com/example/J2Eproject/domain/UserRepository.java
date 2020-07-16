package com.example.J2Eproject.domain;

import com.example.J2Eproject.domain.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserRepository {
    User add(User user);
    User getByUsername(String username);
    User getById(String id);
    List<User>findAll();
    Boolean existsByUsername(String username);
    Boolean existsById(ObjectId id);
    Boolean existsByEmail(String email);
}
