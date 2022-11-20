package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;

public abstract class SimpleNamedConfigurationService <
        ModelClass extends NamedConfigurationModel, 
        InputDtoClass extends NamedConfigurationInputDto
    > 
extends NamedConfigurationService<ModelClass, InputDtoClass> {

    public SimpleNamedConfigurationService(NamedConfigurationRepository<ModelClass> repository, Class<ModelClass> modelClass) {
        super(repository, modelClass);
    }

    @Override
    protected final void updateBase(ModelClass model, InputDtoClass input) {
        setIfNotNull(input.getName(), model::setName);
    }

    @Override
    protected final void updateEmbeddedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected final void updateAssociatedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected final void deleteAssociatedValues(ModelClass model) {    }
}
