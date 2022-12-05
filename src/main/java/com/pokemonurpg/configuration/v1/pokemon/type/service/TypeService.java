package com.pokemonurpg.configuration.v1.pokemon.type.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.entities.v1.pokemon.TypeRepository;

@Service
public class TypeService extends SimpleNamedConfigurationService<Type, TypeInputDto> {

    @Autowired
    public TypeService(TypeRepository repository) {
        super(repository, Type.class);
    }
}
