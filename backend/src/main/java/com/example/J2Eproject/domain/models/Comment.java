package com.example.J2Eproject.domain.models;

import javax.validation.constraints.NotBlank;

public class Comment {

    private String id;
    private String content;
    private String author;
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
