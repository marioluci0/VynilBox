package com.vynilbox.user;

import com.vynilbox.abstracts.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * This class intend to be used as a model
 * of the users, to facilitate operations
 * that requires users elements.
 * It has the constructors, getters, setters
 * and toString methods.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class User extends Account {

    //Fields
    private String name;
    private List<Integer> favSongs = new ArrayList<>();
    private List<Integer> favArtist = new ArrayList<>();

    //constructor
    public User() {
        this(0, null, null, null, null, null, null);
    }

    public User(int id, String name, String username, String email, String password, int[] favSongs, int[]favArtists) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        if(favSongs != null) {
            this.setFavSongs(favSongs);
        }
        if(favArtists != null) {
            this.setFavArtists(favArtists);
        }
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getFavArtists() {
        return favArtist;
    }

    public void setFavArtists (int[] favArtists) {
        for(int i=0; i<favArtists.length;i++)
            this.favArtist.add(favArtists[i]);
    }

    public List<Integer> getFavSongs() {
        return favSongs;
    }

    public void setFavSongs(int[] favSongs) {
        for(int i=0;i<favSongs.length;i++)
            this.favSongs.add(favSongs[i]);
    }

    //Adds and Removes from lists
    public void addFavSong(int songId){
        favSongs.add(songId);
    }

    public void removeFavSong(int songId){
        favSongs.remove((Integer)songId);
    }

    public void addFavArtist(int artistId)
    {
        favArtist.add(artistId);
    }

    public void removeFavArtist(int artistId)
    {
        favArtist.remove((Integer)artistId);
    }
}
