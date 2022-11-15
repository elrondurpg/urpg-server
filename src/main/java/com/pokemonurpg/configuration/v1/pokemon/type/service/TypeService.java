package com.pokemonurpg.configuration.v1.pokemon.type.service;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService extends SimpleNamedConfigurationService<Type, TypeInputDto> {

    @Autowired
    public TypeService(NamedConfigurationRepository<Type> repository) {
        super(repository, Type.class);
    }
}
