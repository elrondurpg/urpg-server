package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;

public abstract class SimpleNamedConfigurationService <
        ModelClass extends ConfigurationModel, 
        InputDtoClass extends ConfigurationInputDto
    > 
extends NamedConfigurationService<ModelClass, InputDtoClass> {

    public SimpleNamedConfigurationService(NamedConfigurationRepository<ModelClass> repository) {
        super(repository);
    }

    @Override
    protected void updateEmbeddedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected void updateAssociatedValues(ModelClass model, InputDtoClass input) {    }

    @Override
    protected void deleteAssociatedValues(ModelClass model) {    }
}
