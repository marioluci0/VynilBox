package com.vynilbox.review;

import com.vynilbox.exceptions.NoResultsException;
import com.vynilbox.song.SongService;

/**
 * This class intend to be used as a model
 * of the review, to facilitate operations
 * that requires review elements.
 * It contains some getters and setters,
 * beyond has the toString method overrided
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class Review {
    //Fields
    private int id;
    private int stars;
    private String text;
    private int songId;
    private int userId;

    //Constructor
    public Review(int id, int stars, String text, int songId, int userId) {
        this.id = id;
        this.stars = stars;
        this.text = text;
        this.songId = songId;
        this.userId = userId;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSongId() {
        return songId;
    }

    //toString
    @Override
    public String toString() {
        try {
            return "Review de: " + new SongService().getSongById(songId);
        } catch (NoResultsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
