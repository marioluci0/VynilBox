package com.vynilbox.artists;

import java.util.List;

/**
 * Used for facilitate and make a clean code.
 * Contains all the attributes of the Song
 * in just one record.
 * @param name artist name
 * @param description artist description
 * @param songs songs made by them
 */
public record ArtistRegisterData (String name, String description, List<Integer> songs) {
}
