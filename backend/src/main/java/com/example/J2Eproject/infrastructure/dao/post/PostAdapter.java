package com.example.J2Eproject.infrastructure.dao.post;

import com.example.J2Eproject.domain.models.Post;

public class PostAdapter {

    public static Post toPost(MongoPost mongoPost) {
        return new Post(mongoPost.getTitle(), mongoPost.getContent(), mongoPost.getAuthor(), mongoPost.getGifUrl());
    }

    public static MongoPost toMongoPost(Post post) {
        return new MongoPost(post.getTitle(), post.getContent(), post.getAuthor(), post.getGifUrl());
    }
}
