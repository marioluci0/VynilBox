package com.vynilbox.abstracts;

import java.sql.Connection;

/**
 * This class intend to be used as base to
 * all Data Access Object, it contains attributes
 * and methods that exist in all of them.
 * Beyond that, it allows to avoid unnecessary
 * repetitive codes.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public abstract class DAO {
    protected Connection connection;

    public DAO(Connection connection) {this.connection = connection;}
}
