package com.example.J2Eproject.post;

import com.example.J2Eproject.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "posts")
public class Post {
    @Id
    String id;

    @NotBlank
    @Size(max = 100)
    String title;

    @NotBlank
    String content;

    @NotBlank
    String author;

    public Post() {
    }

    public Post(@NotBlank @Size(max = 100) String title, @NotBlank String content, @NotBlank String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
