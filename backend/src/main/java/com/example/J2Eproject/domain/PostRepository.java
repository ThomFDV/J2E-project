package com.example.J2Eproject.domain;

import com.example.J2Eproject.domain.models.Post;

import java.util.List;

public interface PostRepository {
    Post byId(String id);
    Post add(Post post);
    List<Post> getAll();
    void deleteById(String id);
}
