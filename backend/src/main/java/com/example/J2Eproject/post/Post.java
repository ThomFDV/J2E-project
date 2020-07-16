package com.example.J2Eproject.post;

import com.example.J2Eproject.comment.Comment;
import com.example.J2Eproject.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "posts")
public class Post {
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
    private Set<Comment> comments = new HashSet<>();

    public Post() {
    }

    public Post(@NotBlank @Size(max = 100) String title, @NotBlank String content, @NotBlank String author, @NotBlank String gifUrl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.gifUrl = gifUrl;
    }

    public Post(@NotBlank @Size(max = 100) String title, @NotBlank String content, @NotBlank String author, @NotBlank String gifUrl, Set<Comment> comments) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.gifUrl = gifUrl;
        this.comments = comments;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
