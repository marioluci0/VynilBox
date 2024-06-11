package com.vynilbox.admin;

import com.vynilbox.abstracts.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class intend to be used as the admin Data Access Object,
 * i.e., it will make the process of doing queries into the database and
 * returning it results.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class AdminDAO extends DAO {

    /**
     * Constructor that receives the connection and establish it
     * to allows the database communication
     * @param connection database connection
     */
    public AdminDAO(Connection connection) {super(connection);}

    /**
     * Authentication method that checks if the admin credentials
     * are valid.
     * It returns an Admin, which is null in case of not existing
     * an admin with the credentials passed.
     * @param email inputted email
     * @param password inputted password
     * @return the Admin Object,
     *            in case of not corresponding => returns null
     */
    public Admin validate (String email, String password){
        PreparedStatement ps;
        ResultSet rs;
        Admin admin = null;

        String sql = "SELECT * FROM admins WHERE email like ? AND password like ?";


        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt(1));
                admin.setUsername(rs.getString(2));
                admin.setEmail(rs.getString(3));
                admin.setPassword(rs.getString(4));
            }

            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return admin;
    }
}
