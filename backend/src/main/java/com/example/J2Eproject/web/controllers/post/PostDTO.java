package com.example.J2Eproject.web.controllers.post;

public class PostDTO {
    private String title;
    private String content;
    private String gifUrl;

    public PostDTO() {
    }

    public PostDTO(String title, String content, String gitfUrl) {
        this.title = title;
        this.content = content;
        this.gifUrl = gitfUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getGifUrl() {
        return gifUrl;
    }
}
