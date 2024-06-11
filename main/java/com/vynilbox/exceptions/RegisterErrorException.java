package com.vynilbox.exceptions;

/**
 * This class intend to be used to specify problems
 * with register accounts (users and admins)
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */

public class RegisterErrorException extends RuntimeException{
    public RegisterErrorException() {
        super("Erro ao registrar dados!!!");
    }
}
