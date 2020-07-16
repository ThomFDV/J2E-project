package com.example.J2Eproject.infrastructure.controller.post;

import com.example.J2Eproject.domain.models.Post;
import com.example.J2Eproject.use_case.services.PostService;
import com.example.J2Eproject.use_case.services.authent.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAll();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String postId) {
        Post post = postService.getById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto(post));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostDTO> addPost(@Valid @RequestBody PostDTO postDTO) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Post post = postService.add(new Post(postDTO.getTitle(),postDTO.getContent(), postDTO.getGifUrl(), username));
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDto(post), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostDTO> updatePost(@PathVariable String postId, @Valid @RequestBody PostDTO postDTO) {
        Post post = postService.getById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            if (postDTO.getTitle() != null) {
                post.setTitle(postDTO.getTitle());
            }
            if (postDTO.getContent() != null) {
                post.setContent(postDTO.getContent());
            }
            if (postDTO.getGifUrl() != null) {
                post.setGifUrl(postDTO.getGifUrl());
            }

        return ResponseEntity.ok(toDto(postService.add(post)));
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletePostById(@PathVariable String postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    private PostDTO toDto(Post post) {
        return new PostDTO(post.getTitle(), post.getContent(), post.getGifUrl());
    }
}
