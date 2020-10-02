package com.everis.desafio_campanha.exceptions;

public class CampanhaNotFoundException extends RuntimeException {
    public CampanhaNotFoundException(String message) {
        super(message);
    }
}
