package com.vynilbox.artists;

import com.vynilbox.abstracts.BasicFields;

import java.util.List;

/**
 * This class intend to be used as a model
 * of the artist, to facilitate operations
 * that requires artists elements.
 * It has a toString method used to print an
 * artist (that is only necessary to print
 * them name).
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class Artist extends BasicFields {
    //Fields
    private List<Integer> songs = null;

    //Constructor
    public Artist(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //toString
    @Override
    public String toString(){
        return name;
    }
}
