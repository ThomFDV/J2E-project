package com.example.J2Eproject.infrastructure.dao.gif;

import com.example.J2Eproject.domain.models.Gif;

public class GifAdapter {

    public static Gif convertToGif(MongoGif mongoGif) {
        return new Gif(mongoGif.getUrl(), mongoGif.getName(), mongoGif.getFavorite());
    }

    public static MongoGif convertToMongoGif(Gif gif) {
        return new MongoGif(gif.getUrl(), gif.getName(), gif.getFavorite());
    }
}
