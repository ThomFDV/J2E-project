package com.example.J2Eproject.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotBlank
    private String postId;

    public Comment() {
    }

    public Comment(@NotBlank String content, @NotBlank String author, @NotBlank String postId) {
        this.content = content;
        this.author = author;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
