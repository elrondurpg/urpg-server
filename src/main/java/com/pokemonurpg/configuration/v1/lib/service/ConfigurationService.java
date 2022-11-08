package com.pokemonurpg.configuration.v1.lib.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.repository.ConfigurationRepository;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;

public abstract class ConfigurationService<
        ModelClass extends ConfigurationModel, 
        InputDtoClass extends ConfigurationInputDto
    > {

    protected ConfigurationRepository<ModelClass> repository;

    public ConfigurationService(ConfigurationRepository<ModelClass> repository) {
        this.repository = repository;
    }

    public Page<ModelClass> find(FilterableGetParams<ModelClass> params) {
        Example<ModelClass> example = params.asExample();
        if (example != null) {
            return repository.findAll(params.asExample(), params.asPageRequest());
        }
        else return repository.findAll(params.asPageRequest());
    }

    public ModelClass findByDbid(int dbid) {
        return repository.findByDbid(dbid);
    }

    public ModelClass create(InputDtoClass input) {
        ModelClass model = createBase(input);
        updateEmbeddedValues(model, input);
        repository.save(model);
        updateAssociatedValues(model, input);
        return model;
    }

    protected abstract ModelClass createBase(InputDtoClass input);

    public ModelClass update(InputDtoClass input, int dbid) {
        ModelClass model = repository.findByDbid(dbid);
        if (model != null) {
            updateBase(model, input);
            updateEmbeddedValues(model, input);
            repository.save(model);
            updateAssociatedValues(model, input);
        }
        return model;
    }

    protected abstract void updateBase(ModelClass model, InputDtoClass input);
    protected abstract void updateEmbeddedValues(ModelClass model, InputDtoClass input);
    protected abstract void updateAssociatedValues(ModelClass model, InputDtoClass input);

    public ModelClass delete(int dbid) {
        ModelClass model = repository.findByDbid(dbid);
        deleteAssociatedValues(model);
        repository.delete(model);
        return model;
    }

    protected abstract void deleteAssociatedValues(ModelClass model);
}
