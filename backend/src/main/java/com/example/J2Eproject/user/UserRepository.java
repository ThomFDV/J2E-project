package com.example.J2Eproject.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(ObjectId id);

    Boolean existsByUsername(String username);

    Boolean existsById(ObjectId id);

    Boolean existsByEmail(String email);

}
