package com.vynilbox.user;

import java.sql.*;
import java.util.ArrayList;

import com.vynilbox.abstracts.DAO;
import com.vynilbox.exceptions.AccountNotFoundException;
import com.vynilbox.exceptions.AlreadyHaveArtistException;
import com.vynilbox.exceptions.AlreadyHaveSongException;
import com.vynilbox.exceptions.RegisterErrorException;

import static java.lang.Integer.parseInt;

/**
 * This class intend to be used as the user Data Access Object,
 * i.e., it will make the process of doing queries into the database and
 * returning it results.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */

public class UserDAO extends DAO {

    /**
     * Constructor that receives the connection and establish it
     * to allows the database communication
     * @param connection database connection
     */
    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     * Insert a new user into the database.
     * It checks if it's a new user before register.
     * @param userRegisterData Dataset of the new user.
     * @throws RegisterErrorException In case of already existing a user with the data informed.
     * @throws SQLException In case of occurring an error with the query executed
     */
    public void save (UserRegisterData userRegisterData) throws RegisterErrorException, SQLException {
        String insertCommand = "INSERT INTO `vynilbox_database`.`users`" +
                "(`name`, `username`, `email`, `password`)" +
                " VALUES('"
                + userRegisterData.name() + "', '"
                + userRegisterData.username() + "', '"
                + userRegisterData.email() + "', '"
                + userRegisterData.password() + "');";

        String validationCommand = "SELECT count(1) FROM users WHERE " +
                "email = '" + userRegisterData.email() + "'OR " +
                "username = '" + userRegisterData.username() + "'";
        try {
            Statement sValidationData = connection.createStatement();
            ResultSet queryResult = sValidationData.executeQuery(validationCommand);

            while (queryResult.next()) {
                if (queryResult.getInt(1) > 0) {
                    //Throws the error that already exist a user with the data inputted
                    throw new RegisterErrorException();
                } else {
                    //Insert a new user into the database
                    PreparedStatement psInsertToDB = connection.prepareStatement(insertCommand);
                    psInsertToDB.executeUpdate();
                    break;
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Authenticate the email and password.
     * Checks into the 'users' table if exists
     * a user with this data.
     * @param userRegisterData User's data.
     * @return User's object with all the data of the user logged
     * @throws AccountNotFoundException In case of not existing a user with the info passed
     */
    public User validate (UserRegisterData userRegisterData) throws AccountNotFoundException {
        PreparedStatement ps;
        ResultSet rs;
        User user = null;
        String sql = "SELECT * FROM users WHERE email=?";


        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userRegisterData.email());

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));

                int[] favSongsInt = new int[0];
                if (rs.getString(6) != null) {
                    String[] favSongs = rs.getString(6).split("#");
                    favSongsInt = new int[favSongs.length];
                    for (int i = 0; i < favSongs.length; i++) {
                        favSongsInt[i] = parseInt(favSongs[i]);
                    }
                }

                user.setFavSongs(favSongsInt);

                int[] favArtistsInt = new int[0];
                if (rs.getString(7) != null) {
                    String[] favArtists = rs.getString(7).split("#");
                    favArtistsInt = new int[favArtists.length];
                    for (int i = 0; i < favArtists.length; i++) {
                        favArtistsInt[i] = parseInt(favArtists[i]);
                    }
                }

                user.setFavArtists(favArtistsInt);

            }

            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if (user != null)
            return user;
        else
            throw new AccountNotFoundException();
    }

    /**
     * Update of user data.
     * @param userRegisterData New dataset of user.
     * @param id User id.
     */
    public void alterUser(UserRegisterData userRegisterData, int id) {
        String alterCommand = "UPDATE `vynilbox_database`.`users` " +
                "SET " +
                "`name` = '"+ userRegisterData.name() +"', " +
                "`username` = '"+ userRegisterData.username() +"', " +
                "`email` = '"+ userRegisterData.email() +"', " +
                "`password` = '"+ userRegisterData.password() +"' " +
                "WHERE" +
                " `id` = "+ id +";";

        try {
            PreparedStatement sAlter = connection.prepareStatement(alterCommand);
            sAlter.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add a song to the favorite songs list.
     * Checks if the songs isn't already on the list.
     * @param songId Id of new song added.
     * @param userId Id of user.
     * @throws AlreadyHaveSongException In case the song already is on the list.
     */
    public void addFavSong(int songId, int userId) throws AlreadyHaveSongException{
        String[] favSongsIds = null;
        int[] favSongsIntIds;
        boolean isHere = false;
        String allFavSongs = null;

        String getFavSongs = "SELECT id_fav_songs FROM users WHERE id = "+ userId +";";

        try {
            Statement sGetFavSongs = connection.createStatement();
            ResultSet queryGetFavSongs = sGetFavSongs.executeQuery(getFavSongs);

            while(queryGetFavSongs.next()) {
                if(queryGetFavSongs.getString(1) != null) {
                    favSongsIds = queryGetFavSongs.getString(1).split("#");
                    allFavSongs = queryGetFavSongs.getString(1);
                }
                else {
                    String add1FavSongCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_songs` = '"+ songId +"' WHERE `id` = "+ userId +";";
                    PreparedStatement psadd1FavSong = connection.prepareStatement(add1FavSongCommand);
                    psadd1FavSong.executeUpdate();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        favSongsIntIds = new int[favSongsIds.length];
        for(int i=0;i<favSongsIds.length;i++)
            favSongsIntIds[i] = parseInt(favSongsIds[i]);

        for(int i=0;i<favSongsIntIds.length;i++) {
            if(favSongsIntIds[i] == songId)
                throw new AlreadyHaveSongException();
        }

        String alterFavListCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_songs` = '"+ allFavSongs +"#"+ songId +"' WHERE `id` = "+ userId +";";

        try {
            PreparedStatement psAlterFavList = connection.prepareStatement(alterFavListCommand);
            psAlterFavList.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove song of the list.
     * @param songId Song id.
     * @param userId User id.
     */
    public void removeFavSong(int songId, int userId) {
        String favSongs = null;

        String getFavSongsCommand = "SELECT id_fav_songs FROM users WHERE id = "+ userId +";";

        String setNullCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_songs` = null WHERE `id` = "+ userId +";";

        try {
            Statement sGetSongs = connection.createStatement();
            ResultSet queryGetSongs = sGetSongs.executeQuery(getFavSongsCommand);

            while(queryGetSongs.next()) {
                favSongs = queryGetSongs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            PreparedStatement psSetNull = connection.prepareStatement(setNullCommand);
            psSetNull.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String[] favSongSplitado = favSongs.split("#");
        ArrayList<Integer> favSongsInt = new ArrayList<>();
        for(int i=0;i<favSongSplitado.length;i++) {
            favSongsInt.add(parseInt(favSongSplitado[i]));
        }

        favSongsInt.remove((Integer) songId);

        for (Integer songID : favSongsInt) {
            addFavSong(songID, userId);
        }
    }

    /**
     * Add artist to the favorite artists list.
     * @param artistId Id of the artist.
     * @param userId Id of the user.
     * @throws AlreadyHaveArtistException In case of the artist already be on the list.
     */
    public void addFavArtist(int artistId, int userId) throws AlreadyHaveArtistException {
        String[] favArtistsIds = null;
        int[] favArtistsIntIds;
        boolean isHere = false;
        String allFavArtists = null;

        String getFavArtists = "SELECT id_fav_artists FROM users WHERE id = "+ userId +";";

        try {
            Statement sGetFavArtists = connection.createStatement();
            ResultSet queryGetFavArtists = sGetFavArtists.executeQuery(getFavArtists);

            while(queryGetFavArtists.next()) {
                if(queryGetFavArtists.getString(1) != null) {
                    favArtistsIds = queryGetFavArtists.getString(1).split("#");
                    allFavArtists = queryGetFavArtists.getString(1);
                }
                else {
                    String add1FavArtistCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_artists` = '"+ artistId +"' WHERE `id` = "+ userId +";";
                    PreparedStatement psadd1FavArtist = connection.prepareStatement(add1FavArtistCommand);
                    psadd1FavArtist.executeUpdate();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        favArtistsIntIds = new int[favArtistsIds.length];
        for(int i=0;i<favArtistsIds.length;i++)
            favArtistsIntIds[i] = parseInt(favArtistsIds[i]);

        for(int i=0;i<favArtistsIntIds.length;i++) {
            if(favArtistsIntIds[i] == artistId)
                throw new AlreadyHaveArtistException();
        }

        String alterFavListCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_artists` = '"+ allFavArtists +"#"+ artistId +"' WHERE `id` = "+ userId +";";

        try {
            PreparedStatement psAlterFavList = connection.prepareStatement(alterFavListCommand);
            psAlterFavList.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove artist of the favorite artistst list.
     * @param artistId Artist's id.
     * @param userId User's id.
     */
    public void removeFavArtist(int artistId, int userId) {
        String favArtists = null;

        String getFavArtistsCommand = "SELECT id_fav_artists FROM users WHERE id = "+ userId +";";

        String setNullCommand = "UPDATE `vynilbox_database`.`users` SET `id_fav_artists` = null WHERE `id` = "+ userId +";";

        try {
            Statement sGetArtists = connection.createStatement();
            ResultSet queryGetArtists = sGetArtists.executeQuery(getFavArtistsCommand);

            while(queryGetArtists.next()) {
                favArtists = queryGetArtists.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            PreparedStatement psSetNull = connection.prepareStatement(setNullCommand);
            psSetNull.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String[] favArtistsSplitado = favArtists.split("#");
        ArrayList<Integer> favArtistsInt = new ArrayList<>();
        for(int i=0;i<favArtistsSplitado.length;i++) {
            favArtistsInt.add(parseInt(favArtistsSplitado[i]));
        }

        favArtistsInt.remove((Integer) artistId);

        for (Integer artistID : favArtistsInt) {
            addFavArtist(artistID, userId);
        }
    }
}
