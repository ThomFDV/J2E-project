package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.infrastructure.persistence.dal.PostRepository;
import com.example.J2Eproject.infrastructure.persistence.entities.Post;
import com.example.J2Eproject.infrastructure.controller.post.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getById(String id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() { return postRepository.findAll(); }

    public Post add(String title, String content, String gifUrl, String username) {
        return postRepository.save(new Post(title, content, username, gifUrl));
    }

    public Post update(PostDTO postDTO, String postId) {
        return postRepository.findById(postId).map(post -> {
            if (postDTO.getTitle() != null) {
                post.setTitle(postDTO.getTitle());
            }
            if (postDTO.getContent() != null) {
                post.setContent(postDTO.getContent());
            }
            if (postDTO.getGifUrl() != null) {
                post.setGifUrl(postDTO.getGifUrl());
            }
            return postRepository.save(post);
        }).orElseThrow(RuntimeException::new);
    }

    public void delete(String postId) {
        postRepository.deleteById(postId);
    }
}
