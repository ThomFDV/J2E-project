package com.example.J2Eproject.infrastructure.controller.gif;

import com.example.J2Eproject.domain.models.Gif;
import com.example.J2Eproject.domain.models.User;
import com.example.J2Eproject.use_case.services.GifService;
import com.example.J2Eproject.use_case.services.authent.UserDetailsImpl;
import com.example.J2Eproject.use_case.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/gif")
public class GifController {

    private final GifService service;
    private final UserService userService;

    @Autowired
    public GifController(GifService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity AddToFavorite(@RequestBody @Valid GifDTO gifDTO, UriComponentsBuilder uriBuilder) {
        if (gifDTO.getName().isEmpty() || gifDTO.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userId = userDetails.getId();

        User user = userService.getById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Gif gif = service.add(new Gif(gifDTO.getUrl(), gifDTO.getName(), 0));
        if (gif == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean tmp = user.getMongoGifs().removeIf(
                g -> g.get_id().equals(gif.get_id())
        );
        if (tmp) {
            userService.add(user);
            return ResponseEntity.ok().build();
        }

        //add gif to user
        user.addGif(gif);
        userService.add(user);

        return new ResponseEntity<>(toDto(gif), HttpStatus.CREATED);
    }

    @GetMapping("/{gifId}")
    public ResponseEntity<GifDTO> getGif(@PathVariable("gifId") String gifId) {
        Gif gif = service.getById(gifId);
        if (gif == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto(gif));
    }

    private GifDTO toDto(Gif gif) {
        var body = new GifDTO();
        body.setName(gif.getName());
        body.setUrl(gif.getUrl());

        return body;
    }
}
