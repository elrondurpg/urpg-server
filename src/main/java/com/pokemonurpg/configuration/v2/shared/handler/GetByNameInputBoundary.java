package com.pokemonurpg.configuration.v2.shared.handler;

public interface GetByNameInputBoundary<ResponseType> {
    ResponseType getByName(String name);
}
