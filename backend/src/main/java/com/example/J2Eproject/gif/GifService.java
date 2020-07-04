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
            // TODO link to user
            return repository.save(gif.get());
        }

        return repository.save(new Gif(gifDTO.getName(), gifDTO.getUrl(), 1));
    }

    public Optional<Gif> getById(String id) {
        return repository.findById(id);
    }

    public Boolean canAdd(GifDTO gifDTO) {
        return repository.findByName(gifDTO.getName()).isPresent() && repository.findByUrl(gifDTO.getUrl()).isPresent();
    }


}
