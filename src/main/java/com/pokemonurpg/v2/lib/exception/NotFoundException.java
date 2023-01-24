package com.pokemonurpg.v2.lib.exception;

public class NotFoundException extends IllegalStateException {
    public NotFoundException(String message) {
        super(message);
    }
}
