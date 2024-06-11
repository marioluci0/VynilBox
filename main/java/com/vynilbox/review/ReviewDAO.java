package com.vynilbox.review;

import com.vynilbox.abstracts.DAO;
import com.vynilbox.user.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class intend to be used as the review Data Access Object,
 * i.e., it will make the process of doing queries into the database and
 * returning it results.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class ReviewDAO extends DAO {

    //Constructor
    public ReviewDAO(Connection connection) {
        super(connection);
    }

    /**
     * Insertion of the new review into the table of reviews,
     * using the data passed.
     * @param reviewRegisterData dataset of the new review.
     */
    public void save(ReviewRegisterData reviewRegisterData){
        PreparedStatement ps;

        String sql = "INSERT INTO reviews " +
                "(stars, description, id_song, id_user)" +
                "VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reviewRegisterData.stars());
            ps.setString(2, reviewRegisterData.text());
            ps.setInt(3, reviewRegisterData.songId());
            ps.setInt(4, reviewRegisterData.userId());

            ps.execute();

            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deleting of the review by its id.
     * @param reviewId id of the review.
     */
    public void delete (int reviewId) {
        PreparedStatement ps;

        String sql = "DELETE FROM reviews WHERE id=?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reviewId);

            ps.execute();

            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Database access and selection of the reviews
     * by the use of the userId.
     * @param user owner of the reviews
     * @return ArrayList with the Reviews made by the user
     */
    public ArrayList<Review> getAllReviews(User user){
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Review> allReviews = new ArrayList<>();

        String sql = "SELECT * FROM reviews WHERE id_user=?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());

            rs = ps.executeQuery();

            while(rs.next()) {
                allReviews.add(new Review(
                        rs.getInt(1),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getInt(2)
                ));
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return allReviews;
    }
}
