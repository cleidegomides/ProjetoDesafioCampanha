package com.everis.desafio_campanha.exceptions;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(String message) {
        super(message);
    }
}
