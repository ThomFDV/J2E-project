package com.example.J2Eproject.gif;

import com.example.J2Eproject.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/gif")
public class GifController {

    private final GifService service;

    @Autowired
    public GifController(GifService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> AddToFavorite(@RequestBody @Valid GifDTO gifDTO, UriComponentsBuilder uriBuilder) {
        if (gifDTO.getName().isEmpty() || gifDTO.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        //add the user
        var gif = service.add(gifDTO.getName(), gifDTO.getUrl());

        if (gif == null) {
            return ResponseEntity.notFound().build();
        }

        URI uri = uriBuilder.path("/gif/{gifId}").buildAndExpand(gif.get_id()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{gifId}")
    public ResponseEntity<GifDTO> getGif(@PathVariable("gifId") String gifId) {
        return service
                .getById(gifId)
                .map(this::toDto)
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity<GifDTO> toDto(Gif gif) {
        var body = new GifDTO();
        body.setName(gif.getName());
        body.setUrl(gif.getUrl());

        return ResponseEntity.ok(body);
    }
}
