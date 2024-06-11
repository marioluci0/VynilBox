package com.vynilbox.exceptions;

/**
 * RuntimeException that occurs when the user tries
 * to favorite a song that already is on them
 * favorite artist list
 *
 * @author Gabriel Brum e Mário Lúcio
 * @version 1.0
 * @since 1.0
 */
public class AlreadyHaveSongException extends RuntimeException{
    public AlreadyHaveSongException() {
        super("Essa música ja está entre as favoritas");
    }
}
