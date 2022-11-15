package com.pokemonurpg.configuration.v1.lib.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;

public abstract class SimpleNamedConfigurationService <
        ModelClass extends NamedConfigurationModel, 
        InputDtoClass extends NamedConfigurationInputDto
    > 
extends NamedConfigurationService<ModelClass, InputDtoClass> {

    @Autowired
    public SimpleNamedConfigurationService(NamedConfigurationRepository<ModelClass> repository, Class<ModelClass> modelClass) {
        super(repository, modelClass);
    }

    @Override
    protected void updateBase(ModelClass model, InputDtoClass input) {
        setIfNotNull(input.getName(), model::setName);
    }

    @Override
    protected void updateEmbeddedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected void updateAssociatedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected void deleteAssociatedValues(ModelClass model) {    }
}
