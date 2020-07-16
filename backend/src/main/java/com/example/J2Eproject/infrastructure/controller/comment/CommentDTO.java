package com.example.J2Eproject.infrastructure.controller.comment;

public class CommentDTO {
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
