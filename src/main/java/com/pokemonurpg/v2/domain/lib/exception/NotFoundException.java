package com.pokemonurpg.v2.domain.lib.exception;

public class NotFoundException extends IllegalStateException {
    public NotFoundException(String message) {
        super(message);
    }
}
