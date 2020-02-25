package com.ipiecoles.java.audio.entity;

import javax.persistence.*;

@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artistId")
    private Artist artistId;

    public Album() {
    }

    public Album(Long id, String title, Artist artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artistId;
    }

    public void setArtist(Artist artist) {
        this.artistId = artist;
    }
}
