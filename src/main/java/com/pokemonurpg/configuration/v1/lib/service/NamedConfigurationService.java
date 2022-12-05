package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.entities.v1.shared.NamedRepository;

public abstract class NamedConfigurationService <
        ModelClass extends NamedEntity, 
        InputDtoClass extends NamedConfigurationInputDto
    > 
    extends IndexedConfigurationService<ModelClass, InputDtoClass> {

    protected NamedRepository<ModelClass> repository;

    public NamedConfigurationService(NamedRepository<ModelClass> repository, Class<ModelClass> modelClass) {
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

    @Override
    protected void updateBase(ModelClass model, InputDtoClass input) {
        setIfNotNull(input.getName(), model::setName);
    }
}
