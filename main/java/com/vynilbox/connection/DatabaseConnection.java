package com.vynilbox.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class is used to create the database connection
 * and implements the method to get connection, to deliver
 * the connection to any class in the project, this is
 * allowed by the public characteristic.
 */
public class DatabaseConnection {

    protected Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "vynilbox_database";
        String databaseUser = "root";
        String databasePassword = "thisserverismy134";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return databaseLink;
    }
}
