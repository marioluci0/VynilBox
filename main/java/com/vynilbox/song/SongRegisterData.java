package com.vynilbox.song;

import java.util.List;

/**
 * Used for facilitate and make a clean code.
 * Contains all the attributes of the Song
 * in just one record.
 * @param name song name
 * @param description song description
 * @param releaseYear realease year of the song
 * @param genres genres that it has
 * @param artists artists that made the song
 */
public record SongRegisterData (String name, String description, String releaseYear, List<Integer> genres, List<Integer> artists) {
}
