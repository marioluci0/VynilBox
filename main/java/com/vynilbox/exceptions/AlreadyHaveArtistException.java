package com.vynilbox.exceptions;

/**
 * RuntimeException that occurs when the user tries
 * to favorite an artist that already is on them
 * favorite artist list
 *
 * @author Gabriel Brum e Mário Lúcio
 * @version 1.0
 * @since 1.0
 */
public class AlreadyHaveArtistException extends RuntimeException{
    public AlreadyHaveArtistException(){
        super("Esse artista ja é favorito");
    }
}
