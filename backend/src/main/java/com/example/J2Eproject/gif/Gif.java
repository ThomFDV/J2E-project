package com.example.J2Eproject.gif;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


public class Gif {
    @Id
    private ObjectId _id;
    private String url;
    private String name;
    private int favorite;

    public Gif(String url, String name, int favorite) {
        this.url = url;
        this.name = name;
        this.favorite = favorite;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public ObjectId get_id() {
        return _id;
    }
}


