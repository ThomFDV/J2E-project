package com.example.J2Eproject.post;

public class PostDTO {
    private String title;
    private String content;

    public PostDTO() {
    }

    public PostDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
