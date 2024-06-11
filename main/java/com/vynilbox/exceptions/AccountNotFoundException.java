package com.vynilbox.exceptions;

/**
 * This class intend to be used to specify problems
 * with searches of users/admins that doesn't get
 * a result back (i.e. account doesn't exist)
 *
 * @author Gabriel Brum e Mário Lúcio
 * @version 1.0
 * @since 1.0
 */
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException() {
        super("Usuário não encontrado");
    }
}
