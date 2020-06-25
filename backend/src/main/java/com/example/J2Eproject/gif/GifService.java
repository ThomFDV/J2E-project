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

    public Gif add(String name, String url) {
        var gif = new Gif().setName(name).setUrl(url);
        return repository.save(gif);
    }

    public Optional<Gif> getById(String id) {
        return repository.findById(id);
    }

    public Boolean canAdd(GifDTO gifDTO) {
        return repository.findByName(gifDTO.getName()).isPresent() && repository.findByUrl(gifDTO.getUrl()).isPresent();
    }


}
