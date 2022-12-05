package com.pokemonurpg.configuration.v1.lib.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.entities.v1.shared.UrpgEntity;
import com.pokemonurpg.entities.v1.shared.UrpgRepository;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;

import lombok.Getter;

@Getter
public abstract class ConfigurationService<
        ModelClass extends UrpgEntity, 
        InputDtoClass extends ConfigurationInputDto
    > {

    protected Class<ModelClass> modelClass;
    protected UrpgRepository<ModelClass, ?> repository;

    public ConfigurationService() {}

    public ConfigurationService(UrpgRepository<ModelClass, ?> repository, Class<ModelClass> modelClass) {
        this.repository = repository;
        this.modelClass = modelClass;
    }

    public Page<ModelClass> find(FilterableGetParams<ModelClass> params) {
        Example<ModelClass> example = params.asExample();
        if (example != null) {
            return repository.findAll(params.asExample(), params.asPageRequest());
        }
        else return repository.findAll(params.asPageRequest());
    }

    protected ModelClass callDefaultConstructor() {
        try {
            return modelClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException();
        }
    }

    public ModelClass create(InputDtoClass input) {
        ModelClass model = callDefaultConstructor();
        setKeyValues(model, input);
        updateBase(model, input);
        model.setDefaultValues();
        updateEmbeddedValues(model, input);
        repository.save(model);
        updateAssociatedValues(model, input);
        return model;
    }

    protected void setKeyValues(ModelClass model, InputDtoClass input) {

    }

    protected <T> void setIfNotNull(T value, FieldSetter<T> setter) {
        if (value != null) {
            setter.setValue(value);
        }
    }

    protected void updateBase(ModelClass model, InputDtoClass input) {}
    protected abstract void updateEmbeddedValues(ModelClass model, InputDtoClass input);
    protected abstract void updateAssociatedValues(ModelClass model, InputDtoClass input);

    protected abstract void deleteAssociatedValues(ModelClass model);    
}
