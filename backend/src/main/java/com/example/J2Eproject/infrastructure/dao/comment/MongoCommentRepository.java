package com.example.J2Eproject.infrastructure.dao.comment;

import com.example.J2Eproject.domain.CommentRepository;
import com.example.J2Eproject.domain.models.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoCommentRepository implements CommentRepository {

    private final MongoTemplate mongoTemplate;

    public MongoCommentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Comment> getAll() {
        final List<MongoComment> mongoComments = mongoTemplate.findAll(MongoComment.class);
        return mongoComments.stream().map(CommentAdapter::toComment).collect(Collectors.toList());
    }

    @Override
    public Comment add(Comment comment) {
        MongoComment mongoComment = CommentAdapter.toMongoComment(comment);
        MongoComment savedMongoComment = mongoTemplate.save(mongoComment);
        return CommentAdapter.toComment(savedMongoComment);
    }
}
