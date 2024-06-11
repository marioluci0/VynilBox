package com.vynilbox.artists;

import com.vynilbox.abstracts.DAO;
import com.vynilbox.user.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class intend to be used as the artist Data Access Object,
 * i.e., it will make the process of doing queries into the database and
 * returning it results.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class ArtistDAO extends DAO {

    /**
     * Constructor that receives the connection and establish it
     * to allows the database communication
     * @param connection database connection
     */
    public ArtistDAO(Connection connection) {
        super(connection);
    }

    /**
     * This method is used to get all the list of artists
     * to show at home page.
     * @return an ArrayList of 'Artist' with the results
     */
    public ArrayList<Artist> getAllArtists() {
        ArrayList<Artist> artistsList = new ArrayList<>();

        String sql = "SELECT * FROM artists";

        try {
            Statement sGetArtists = connection.createStatement();
            ResultSet queryGetArtists = sGetArtists.executeQuery(sql);
            while(queryGetArtists.next()) {
                artistsList.add(new Artist(
                        queryGetArtists.getInt(1),
                        queryGetArtists.getString(2),
                        queryGetArtists.getString(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return artistsList;
    }

    /**
     * This method get all artists that are one of the
     * favorites artists of the user, and put them all
     * into an ArrayList that is returned.
     * @param user logged user
     * @return ArrayList with the favorite artists
     */
    public ArrayList<Artist> getAllArtists(User user) {
        ArrayList<Artist> artistsList = new ArrayList<>();
        ArrayList<Integer> artistsIds = (ArrayList<Integer>) user.getFavArtists();

        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM artists WHERE id = ?";

        for(int id : artistsIds) {

            try {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);

                rs = ps.executeQuery();

                while(rs.next()) {
                    artistsList.add(new Artist(
                       rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return artistsList;
    }

    /**
     * This method is to update the data of
     * artists modified by the admin.
     * @param artistRegisterData set of values with all the artist needed data
     * @param id
     */
    public void alterArtist(ArtistRegisterData artistRegisterData, int id) {
        PreparedStatement ps;

        String sql = "UPDATE artists SET " +
                "name = ?," +
                "description = ?" +
                "WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, artistRegisterData.name());
            ps.setString(2, artistRegisterData.description());
            ps.setInt(3, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
