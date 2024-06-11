package com.vynilbox.abstracts;

/**
 * This class intend to be used as base to
 * classes as admin and user.
 * It contains attributes that exist in
 * both classes.
 * Beyond that, it allows to avoid unnecessary
 * repetitive codes.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public abstract class Account {

    //Fields
    protected int id;
    protected String username;
    protected String email;
    protected String password;

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
