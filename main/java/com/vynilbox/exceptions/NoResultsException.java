package com.vynilbox.exceptions;

/**
 * This class intend to be used to specify problems
 * when a search is made and there isn't results
 * available
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class NoResultsException extends RuntimeException {
    public NoResultsException () {
        super("Nenhum resultado foi encontrado!");
    }
}
