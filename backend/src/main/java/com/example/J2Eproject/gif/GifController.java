package com.example.J2Eproject.gif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/gif")
public class GifController {

    private GifRepository repository;

    @Autowired
    public GifController(GifRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<GifDTO> AddToFavorite(HttpServletRequest httpServletRequest, @RequestBody @Valid GifDTO gifDTO) {
        var gif = new Gif()
                .setName(gifDTO.getName())
                .setUrl(gifDTO.getUrl());
        repository.save(gif);
        return ResponseEntity.status(HttpStatus.CREATED).body(gifDTO);
    }
}
