package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.entity.Artist;
import com.ipiecoles.java.audio.exception.EntityConflictException;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;


    @GetMapping(value = "/{id}")
    public Artist getArtistById(@PathVariable("id") Long id)
    {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent())
        {
            return artist.get();
        }
        throw new EntityNotFoundException("L'id " + id + "n'existe pas !");
    }

    @RequestMapping(value="", params = "name")
    public List<Artist> getArtistByName(@RequestParam("name") String name)
    {
        return artistRepository.findByName(name);
    }

    @RequestMapping(value="")
    public Page<Artist> getAllArtists(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam("sortDirection") Sort.Direction sortDirection,
                                      @RequestParam("sortProperty") String sortProperty)

    {
        return artistRepository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
    }

    @PostMapping(value="")
    public Artist createArtist(@RequestBody Artist artist) throws EntityConflictException {
        List<Artist> artistTest = artistRepository.findByName(artist.getName());
        if(artistTest == null)
        {
            return artistRepository.save(artist);
        }
        throw new EntityConflictException("Cet(te) artiste existe déjà");
    }

    @PutMapping(value = "/{id}")
    public Artist updateArtist(@RequestBody Artist artist)
    {
        return artistRepository.save(artist);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Long id)
    {
        artistRepository.deleteById(id);
    }
}