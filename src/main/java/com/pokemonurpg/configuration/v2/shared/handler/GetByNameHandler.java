package com.pokemonurpg.configuration.v2.shared.handler;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GetByNameHandler<EntityType extends ResponseType, ResponseType> implements GetByNameInputBoundary<ResponseType> {
    private NamedRepository<EntityType> repository;

    public ResponseType getByName(String name) {
        ResponseType response = repository.findByName(name);
        if (response == null) {
            response = repository.findFirstByNameStartingWith(name);
        }
        return response;
    }
}
