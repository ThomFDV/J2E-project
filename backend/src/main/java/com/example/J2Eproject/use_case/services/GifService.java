package com.example.J2Eproject.use_case.services;

import com.example.J2Eproject.domain.GifRepository;
import com.example.J2Eproject.domain.models.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    private final GifRepository repository;

    @Autowired
    public GifService(GifRepository repository) {
        this.repository = repository;
    }

    public Gif add(Gif gif) {
        Gif findGif = repository.getByNameAndUrl(gif.getName(), gif.getUrl());
        if (findGif == null) {
            return repository.add(gif);
        }
        findGif.setFavorite(findGif.getFavorite() + 1);
        return repository.add(gif);
    }

    public Gif getById(String id) {
        return repository.getById(id);
    }

}
