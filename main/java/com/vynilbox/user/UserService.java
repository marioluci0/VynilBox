package com.vynilbox.user;

import com.vynilbox.abstracts.Service;
import com.vynilbox.exceptions.AccountNotFoundException;
import com.vynilbox.exceptions.AlreadyHaveArtistException;
import com.vynilbox.exceptions.AlreadyHaveSongException;
import com.vynilbox.exceptions.RegisterErrorException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class intend to allow the communication
 * between the controller and DAO of user.
 * It creates the connection every time, for
 * the reason that the connection will be
 * close and won't overload the database connection.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */

public class UserService extends Service {

    /**
     * Constructor uses the benefits of heritage,
     * usage of 'super()' to create a new DataBaseConnection
     */
    public UserService() {
        super();
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to call the method to register user.
     * @param userRegisterData Dataset of new user.
     * @throws RegisterErrorException In case of catching an error while registering.
     * @throws SQLException In case of catching an error in a database query.
     */
    public void save(UserRegisterData userRegisterData) throws RegisterErrorException, SQLException {
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).save(userRegisterData);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to call the validation method.
     * @param userRegisterData Dataset of user.
     * @return User object with the data form the database.
     * @throws AccountNotFoundException In case of not existing an correspoding account.
     */
    public User validate(UserRegisterData userRegisterData) throws AccountNotFoundException {
        Connection connection = databaseConnection.getConnection();
        return new UserDAO(connection).validate(userRegisterData);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to call the method to update the user data.
     * @param userRegisterData New dataset of user.
     * @param id User id.
     */
    public void alterUser(UserRegisterData userRegisterData, int id) {
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).alterUser(userRegisterData, id);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to do the addition to the database.
     * @param songId Song id.
     * @param userId User id.
     * @throws AlreadyHaveSongException In case of repeating a song.
     */
    public void addFavSong(int songId, int userId) throws AlreadyHaveSongException{
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).addFavSong(songId, userId);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to remove a song of database.
     * @param songId Song id.
     * @param userId User id.
     */
    public void removeFavSong(int songId, int userId) {
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).removeFavSong(songId, userId);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection
     * to add an artist to the database.
     * @param artistId Artist id.
     * @param userId User id.
     * @throws AlreadyHaveArtistException In case of repeating the artist.
     */
    public void addFavArtist(int artistId, int userId) throws AlreadyHaveArtistException {
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).addFavArtist(artistId, userId);
    }

    /**
     * Creates the database connection,
     * instantiate a new UserDAO with the connection.
     * to remove an artist of the list.
     * @param artistId Artist id.
     * @param userId User id.
     */
    public void removeFavArtist(int artistId, int userId) {
        Connection connection = databaseConnection.getConnection();
        new UserDAO(connection).removeFavArtist(artistId, userId);
    }
}
