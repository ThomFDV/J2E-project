package com.example.J2Eproject.gif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GifService {

    private final GifRepository repository;

    @Autowired
    public GifService(GifRepository repository) {
        this.repository = repository;
    }

    public Gif add(GifDTO gifDTO) {
        var gif = repository.findByNameAndUrl(gifDTO.getName(), gifDTO.getUrl());
        if (gif.isPresent()) {
            var toReturn = gif.get();
            toReturn.setFavorite(toReturn.getFavorite() + 1);
            return repository.save(gif.get());
        }

        return repository.save(new Gif(gifDTO.getUrl(), gifDTO.getName(), 1));
    }

    public Optional<Gif> getById(String id) {
        return repository.findById(id);
    }

}
