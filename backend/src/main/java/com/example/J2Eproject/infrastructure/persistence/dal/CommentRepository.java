package com.example.J2Eproject.infrastructure.persistence.dal;

import com.example.J2Eproject.infrastructure.persistence.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
