package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.web.controllers.post.CommentDTO;
import com.example.J2Eproject.infrastructure.persistence.dal.CommentRepository;
import com.example.J2Eproject.infrastructure.persistence.entities.Comment;
import com.example.J2Eproject.infrastructure.persistence.entities.Post;
import com.example.J2Eproject.infrastructure.persistence.dal.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment add(CommentDTO commentDTO, String username, String postId) {
        Post post = this.postRepository.findById(postId).orElse(null);
        Comment comment = this.commentRepository.save(new Comment(commentDTO.getContent(), username, postId));
        if (post != null) {
            post.addComment(comment);
            this.postRepository.save(post);
        } else {
            System.out.println("\n\nNO POST\n\n");
            return null;
        }
        return comment;
    }
}
