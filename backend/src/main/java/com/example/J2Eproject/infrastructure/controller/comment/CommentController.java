package com.example.J2Eproject.infrastructure.controller.comment;

import com.example.J2Eproject.domain.models.Comment;
import com.example.J2Eproject.use_case.services.CommentService;
import com.example.J2Eproject.infrastructure.dao.comment.MongoComment;
import com.example.J2Eproject.use_case.services.authent.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable String postId, @Valid @RequestBody CommentDTO commentDTO) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Comment comment = commentService.add(commentDTO, username, postId);
        System.out.println("\n\nNO COMMENTS\n\n");
        if (comment != null) return ResponseEntity.ok(comment);
        return ResponseEntity.badRequest().build();
    }
}
