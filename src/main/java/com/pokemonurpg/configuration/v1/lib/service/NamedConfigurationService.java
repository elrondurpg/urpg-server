package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;

public abstract class NamedConfigurationService <
        ModelClass extends ConfigurationModel, 
        InputDtoClass extends ConfigurationInputDto
    > 
    extends ConfigurationService<ModelClass, InputDtoClass> {

    protected NamedConfigurationRepository<ModelClass> repository;

    public NamedConfigurationService(NamedConfigurationRepository<ModelClass> repository) {
        super(repository);
        this.repository = repository;
    }

    public ModelClass findByName(String name) {
        ModelClass model = findByNameExact(name);
        if (model == null && name != null) {
            return repository.findFirstByNameStartingWith(name);
        }
        else return model;
    }

    public ModelClass findByNameExact(String name) {
        return repository.findByName(name);
    }
}
