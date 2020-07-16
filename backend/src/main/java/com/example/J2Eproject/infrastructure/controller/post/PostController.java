package com.example.J2Eproject.infrastructure.controller.post;

import com.example.J2Eproject.infrastructure.persistence.entities.Post;
import com.example.J2Eproject.use_case.services.PostService;
import com.example.J2Eproject.use_case.services.authent.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable String postId) {
        Optional<Post> post = postService.getById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity addPost(@Valid @RequestBody PostDTO postDTO) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Post post = postService.add(postDTO.getTitle(),postDTO.getContent(), postDTO.getGifUrl(), username);
        if (post != null) {
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.update(postDTO, postId));
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletePostById(@PathVariable String postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}
