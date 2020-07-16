package com.example.J2Eproject.infrastructure.dao.comment;

import com.example.J2Eproject.domain.models.Comment;

public class CommentAdapter {

    public static Comment toComment(MongoComment mongoComment) {
        return new Comment(mongoComment.getContent(), mongoComment.getAuthor(), mongoComment.getPostId());
    }

    public static MongoComment toMongoComment(Comment comment) {
        return new MongoComment(comment.getContent(), comment.getAuthor(), comment.getPostId());
    }
}
