package com.vynilbox.song;

import com.vynilbox.abstracts.BasicFields;

import java.util.ArrayList;
import java.util.List;

/**
 * This class intend to be used as a model
 * of the songs, to facilitate operations
 * that requires songs elements.
 * It has the constructors, getters, setters
 * and toString methods.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class Song extends BasicFields {
    private String releaseYear;
    private List<Integer> artists = new ArrayList<>();

    //Constructor
    public Song(int id, String name, String description, String releaseYear, int[] artists) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        for(int i=0;i<artists.length;i++)
            this.artists.add(artists[i]);
    }

    //Getters adn setters
    public String getReleaseYear() {
        return releaseYear;
    }

    public List<Integer> getArtists() {
        return artists;
    }

    @Override
    public String toString(){
        return "Música: "+ name;
    }
}
