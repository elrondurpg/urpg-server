package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.IndexedConfigurationRepository;

public abstract class IndexedConfigurationService<
ModelClass extends IndexedConfigurationModel, 
InputDtoClass extends ConfigurationInputDto
> 
extends ConfigurationService<ModelClass, InputDtoClass> {

    protected IndexedConfigurationRepository<ModelClass> repository;

    public IndexedConfigurationService(IndexedConfigurationRepository<ModelClass> repository, Class<ModelClass> modelClass) {
        super(repository, modelClass);
        this.repository = repository;
    }

    public ModelClass findByDbid(int dbid) {
        return repository.findByDbid(dbid);
    }

    public ModelClass update(InputDtoClass input, int dbid) {
        ModelClass model = repository.findByDbid(dbid);
        if (model != null) {
            preupdateAssociatedValues(model, input);
            updateBase(model, input);
            updateEmbeddedValues(model, input);
            repository.save(model);
            updateAssociatedValues(model, input);
        }
        return model;
    }

    protected void preupdateAssociatedValues(ModelClass model, InputDtoClass input) {

    }

    public ModelClass delete(int dbid) {
        ModelClass model = repository.findByDbid(dbid);
        deleteAssociatedValues(model);
        repository.delete(model);
        return model;
    }
}
