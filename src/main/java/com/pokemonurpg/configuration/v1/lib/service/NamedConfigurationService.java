package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;

public abstract class NamedConfigurationService <
        ModelClass extends NamedConfigurationModel, 
        InputDtoClass extends NamedConfigurationInputDto
    > 
    extends IndexedConfigurationService<ModelClass, InputDtoClass> {

    protected NamedConfigurationRepository<ModelClass> repository;

    public NamedConfigurationService(NamedConfigurationRepository<ModelClass> repository, Class<ModelClass> modelClass) {
        super(repository, modelClass);
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
