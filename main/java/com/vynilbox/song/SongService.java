package com.vynilbox.song;

import com.vynilbox.abstracts.Service;
import com.vynilbox.exceptions.NoResultsException;
import com.vynilbox.user.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class intend to allow the communication
 * between the controller and DAO of song.
 * It creates the connection every time, for
 * the reason that the connection will be
 * close and won't overload the database connection.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class SongService extends Service {

    /**
     * Constructor uses the benefits of heritage,
     * usage of 'super()' to create a new DataBaseConnection
     */
    public SongService() {
        super();
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get all songs registered.
     * @return List with all songs.
     */
    public ArrayList<Song> getAllSongs() {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getAllSongs();
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get a list with the name of all songs.
     * @return List with all names of the songs;
     */
    public String[] getAllSongsToString() {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getAllSongsToString();
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get the user's favorite songs.
     * @param user Object of the user logged.
     * @return List with the favorite songs.
     */
    public ArrayList<Song> getAllSongs(User user) {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getAllSongs(user);
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get all the artists names that made the song.
     * @param song Song that desire to get the artists.
     * @return List with the artists name.
     */
    public ArrayList<String> getArtistsNames(Song song) {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getArtistsNames(song);
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get a song by its id.
     * @param id Song id.
     * @return Song with the respective id.
     * @throws NoResultsException In case of not existing a result.
     */
    public Song getSongById(int id) throws NoResultsException {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getSongById(id);
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Get a song by its name.
     * @param songName Song's name.
     * @return Song with the respective name.
     * @throws NoResultsException In case of not existing a song with the name passed.
     */
    public Song getSongByName(String songName) throws NoResultsException {
        Connection connection = databaseConnection.getConnection();
        return new SongDAO(connection).getSongByName(songName);
    }

    /**
     * Creates the database connection,
     * instantiate a new SongDAO with the connection.
     * Update a song determinated by the id
     * @param songRegisterData Dataset with the song data.
     * @param id Song id.
     */
    public void alterSong(SongRegisterData songRegisterData, int id) {
        Connection connection = databaseConnection.getConnection();
        new SongDAO(connection).alterSong(songRegisterData, id);
    }
}
