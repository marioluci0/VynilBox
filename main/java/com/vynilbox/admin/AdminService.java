package com.vynilbox.admin;

import com.vynilbox.abstracts.Service;

import java.sql.Connection;

/**
 * This class intend to allow the communication
 * between the controller and DAO of admin.
 * It creates the connection every time, for
 * the reason that the connection will be
 * close and won't overload the database connection.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class AdminService extends Service {
    //Constructor
    public AdminService() {
        super();
    }

    /**
     * Creates a connection with the database
     * and instantiate a AdminDAO to deal with
     * the validation of credentials.
     * @param email inputted email
     * @param password inputted password
     * @return the Admin Object,
     *              in case of not corresponding => returns null
     */
    public Admin validate (String email, String password) {
        Connection connection = databaseConnection.getConnection();
        return new AdminDAO(connection).validate(email, password);
    }
}
