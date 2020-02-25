package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.entity.Album;
import com.ipiecoles.java.audio.repository.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping(value = "")
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") Long id) {
        albumRepository.deleteById(id);
    }
}