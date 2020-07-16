package com.example.J2Eproject.infrastructure.dao.post;

import com.example.J2Eproject.infrastructure.dao.comment.MongoComment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "posts")
public class MongoPost {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotBlank
    private String gifUrl;

    @DBRef
    private Set<MongoComment> mongoComments = new HashSet<>();

    public MongoPost() {
    }

    public MongoPost(@NotBlank @Size(max = 100) String title, @NotBlank String content, @NotBlank String author, @NotBlank String gifUrl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.gifUrl = gifUrl;
    }

    public MongoPost(@NotBlank @Size(max = 100) String title, @NotBlank String content, @NotBlank String author, @NotBlank String gifUrl, Set<MongoComment> mongoComments) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.gifUrl = gifUrl;
        this.mongoComments = mongoComments;
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

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public Set<MongoComment> getMongoComments() {
        return mongoComments;
    }

    public void setMongoComments(Set<MongoComment> mongoComments) {
        this.mongoComments = mongoComments;
    }

    public void addComment(MongoComment mongoComment) {
        this.mongoComments.add(mongoComment);
    }
}
