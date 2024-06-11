package com.vynilbox.review;

import com.vynilbox.abstracts.Service;
import com.vynilbox.user.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class intend to allow the communication
 * between the controller and DAO of review.
 * It creates the connection every time, for
 * the reason that the connection will be
 * close and won't overload the database connection.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class ReviewService extends Service {

    //Constructor
    public ReviewService() {
        super();
    }

    /**
     * This method creates a database connection,
     * instantiate a new ReviewDAO to enable the use
     * of the method that saves the review.
     * @param reviewRegisterData set of new review's data
     */
    public void save(ReviewRegisterData reviewRegisterData) {
        Connection connection = databaseConnection.getConnection();
        new ReviewDAO(connection).save(reviewRegisterData);
    }


    /**
     * Creates a database connection,
     * instantiate a ReviewDAO to allows
     * the removal of the review by its id.
     * @param reviewId id of the target review
     */
    public void delete(int reviewId) {
        Connection connection = databaseConnection.getConnection();
        new ReviewDAO(connection).delete(reviewId);
    }

    /**
     * Creates a database connection,
     * instantiate a ReviewDAO to access
     * the 'getAllReviews(user)' method,
     * which returns a list with the user's reviews.
     * @param user owner of the reviews
     * @return ArrayList with Reviews of the user
     */
    public ArrayList<Review> getAllReviews(User user) {
        Connection connection = databaseConnection.getConnection();
        return new ReviewDAO(connection).getAllReviews(user);
    }
}
