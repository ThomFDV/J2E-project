package com.example.J2Eproject.domain;


import com.example.J2Eproject.domain.models.Gif;

public interface GifRepository {
    Gif getByNameAndUrl(String name, String url);
    Gif add(Gif gif);
    Gif getById(String id);
}
