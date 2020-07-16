package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.domain.CommentRepository;
import com.example.J2Eproject.domain.PostRepository;
import com.example.J2Eproject.domain.models.Comment;
import com.example.J2Eproject.domain.models.Post;
import com.example.J2Eproject.infrastructure.controller.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository mongoPostRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.mongoPostRepository = postRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment add(CommentDTO commentDTO, String username, String postId) {
        Post post = mongoPostRepository.byId(postId);
        Comment comment = this.commentRepository.add(new Comment(commentDTO.getContent(), username, postId));
        if (post != null) {
            post.addComment(comment);
            this.mongoPostRepository.add(post);
        } else {
            System.out.println("\n\nNO POST\n\n");
            return null;
        }
        return comment;
    }
}
