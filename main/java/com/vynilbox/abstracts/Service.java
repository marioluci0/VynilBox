package com.vynilbox.abstracts;

import com.vynilbox.connection.DatabaseConnection;

/**
 * This class intend to be used as base to
 * all Service Classes, it contains attributes
 * and methods that exist in all of them.
 * Beyond that, it allows to avoid unnecessary
 * repetitive codes.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public abstract class Service {

    protected DatabaseConnection databaseConnection;

    public Service() {
        this.databaseConnection = new DatabaseConnection();
    }
}
