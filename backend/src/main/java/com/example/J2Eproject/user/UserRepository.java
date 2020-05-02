package com.example.J2Eproject.user;

import com.example.J2Eproject.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {

    User findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    Optional<User> findByUsername(String username);

    //Optional<User> findById(ObjectId _id);

}
