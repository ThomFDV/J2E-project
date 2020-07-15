package com.example.J2Eproject.comment;

import com.example.J2Eproject.post.Post;
import com.example.J2Eproject.post.PostRepository;
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
