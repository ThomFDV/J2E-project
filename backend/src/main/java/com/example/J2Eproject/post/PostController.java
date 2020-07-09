package com.example.J2Eproject.post;

import com.example.J2Eproject.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
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

    @GetMapping("/{postId}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable String postId) {
        Optional<Post> post = postService.getById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity addPost(@Valid @RequestBody PostDTO postDTO) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Post post = postService.add(postDTO, username);
        if (post != null) {
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.update(postDTO, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePostById(@PathVariable String postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}