package com.example.J2Eproject.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getById(String id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() { return postRepository.findAll(); }

    public Post add(PostDTO post, String username) {
        return postRepository.save(new Post(post.getTitle(), post.getContent(), username));
    }

    public Post update(PostDTO postDTO, String postId) {
        return postRepository.findById(postId).map(post -> {
            if (postDTO.getTitle() != null) {
                post.title = postDTO.getTitle();
            }
            if (postDTO.getContent() != null) {
                post.content = postDTO.getContent();
            }
            return postRepository.save(post);
        }).orElseThrow(RuntimeException::new);
    }

    public void delete(String postId) {
        postRepository.deleteById(postId);
    }
}
