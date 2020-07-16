package com.example.J2Eproject.infrastructure.persistence.dal;

import com.example.J2Eproject.infrastructure.persistence.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
