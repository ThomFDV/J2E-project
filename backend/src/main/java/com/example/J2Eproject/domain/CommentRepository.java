package com.example.J2Eproject.domain;

import com.example.J2Eproject.domain.models.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> getAll();
    Comment add(Comment comment);
}
