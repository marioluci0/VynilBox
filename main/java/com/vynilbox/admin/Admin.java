package com.vynilbox.admin;

import com.vynilbox.abstracts.Account;

/**
 * This class intend to be used as a model
 * of the admin, to facilitate operations
 * that requires admin elements.
 * It only has two constructors and a
 * method to get the Admin username
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class Admin extends Account {

    //Constructors
    public Admin() {
        this(0, null, null, null);
    }
    public Admin(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Getter
    public String getUsername() {
        return this.username;
    }
}
