package com.example.J2Eproject.gif;

import com.example.J2Eproject.user.User;
import com.example.J2Eproject.user.UserDetailsImpl;
import com.example.J2Eproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<?> AddToFavorite(@RequestBody @Valid GifDTO gifDTO, UriComponentsBuilder uriBuilder) {
        if (gifDTO.getName().isEmpty() || gifDTO.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userId = userDetails.getId();
        User user = userService.getById(userId).orElseThrow(() -> new RuntimeException("Error. User not found with id: " + userId));
        var gif = service.add(gifDTO);
        if (gif == null) {
            return ResponseEntity.notFound().build();
        }

        var tmp = user.getGifs().removeIf(
                g -> g.get_id().equals(gif.get_id())
        );
        if (tmp) {
            userService.save(user);
            return ResponseEntity.ok().build();
        }

        //add gif to user
        user.addGif(gif);
        userService.save(user);

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
