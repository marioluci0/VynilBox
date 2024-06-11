package com.vynilbox.abstracts;

/**
 * This class intend to be used as base to
 * classes as artist and song.
 * It contains attributes that exist in
 * both classes.
 * Beyond that, it allows to avoid unnecessary
 * repetitive codes.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public abstract class BasicFields{
    //Fields
    protected int id;
    protected String name;
    protected String description;

    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
