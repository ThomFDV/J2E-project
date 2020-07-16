package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.domain.PostRepository;
import com.example.J2Eproject.domain.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getById(String id) {
        return postRepository.byId(id);
    }

    public List<Post> getAll() { return postRepository.getAll(); }

    public Post add(Post post) {
        return postRepository.add(post);
    }

    public void delete(String postId) {
        postRepository.deleteById(postId);
    }
}
