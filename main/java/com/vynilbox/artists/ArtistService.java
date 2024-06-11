package com.vynilbox.artists;

import com.vynilbox.abstracts.Service;
import com.vynilbox.user.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class intend to allow the communication
 * between the controller and DAO of artists.
 * It creates the connection every time, for
 * the reason that the connection will be
 * closed and won't overload the database connection.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class ArtistService extends Service {

    //Constructor
    public ArtistService() {
        super();
    }

    /**
     * Create the database connection,
     * instantiate an AdminDAO to use the method
     * 'getAllArtists' and returns it result
     * @return ArrayList with all artists registered
     */
    public ArrayList<Artist> getAllArtists() {
        Connection connection = databaseConnection.getConnection();
        return new ArtistDAO(connection).getAllArtists();
    }

    /**
     * Create the database connection,
     * instantiate an ArtistDAO to use the method
     * 'getAllArtist(user)' that gets fav artists
     * of the users.
     * @param user logged user
     * @return ArrayList with all user's fav artists
     */
    public ArrayList<Artist> getAllArtists(User user) {
        Connection connection = databaseConnection.getConnection();
        return new ArtistDAO(connection).getAllArtists(user);
    }

    /**
     * Create the database connection,
     * instantiate an ArtistDAO to use the method
     * 'alterArtist' that update the artist data.
     * @param artistRegisterData set of new data inputted
     * @param id artist's id
     */
    public void alterArtist(ArtistRegisterData artistRegisterData, int id) {
        Connection connection = databaseConnection.getConnection();
        new ArtistDAO(connection).alterArtist(artistRegisterData, id);
    }
}
