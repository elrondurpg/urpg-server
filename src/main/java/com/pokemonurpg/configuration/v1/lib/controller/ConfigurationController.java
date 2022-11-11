package com.pokemonurpg.configuration.v1.lib.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;
import com.pokemonurpg.configuration.v1.lib.config.PagedConfiguration;
import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.service.ConfigurationService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import lombok.Getter;

public abstract class ConfigurationController<
        ModelClass extends ConfigurationModel, 
        FilterableGetParamsClass extends FilterableGetParams<ModelClass>,
        InputDtoClass extends ConfigurationInputDto> {

    @Getter
    protected ConfigurationService<ModelClass, InputDtoClass> service;

    @Getter
    protected final Class<? extends ConfigurationViews.V1> idViewClass;
    @Getter
    protected final Class<? extends ConfigurationViews.V1> briefViewClass;
    @Getter
    protected final Class<? extends ConfigurationViews.V1> fullViewClass;

    public ConfigurationController(ConfigControllerDefinition definition, ConfigurationService<ModelClass, InputDtoClass> service) {
        this.idViewClass = definition.getIdViewClass();
        this.briefViewClass = definition.getBriefViewClass();
        this.fullViewClass = definition.getFullViewClass();
        this.service = service;
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    PagedConfiguration find(@Valid FilterableGetParamsClass params) throws JsonProcessingException {
        Page<ModelClass> page = service.find(params);
        Class<? extends ConfigurationViews.V1> view = getViewByDetailLevel(params.getDetailLevel());
        return new PagedConfiguration(page, view);
    }

    @Validated(ObjectCreation.class)
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_AUTHORIZED)
    @PostMapping
    public @ResponseBody
    MappingJacksonValue create(@Valid @RequestBody InputDtoClass input) {
        ModelClass model = service.create(input);
        return createMappingJacksonValueForModelWithView(model, fullViewClass);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_AUTHORIZED)
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    MappingJacksonValue update(@Valid @RequestBody InputDtoClass input, @PathVariable int dbid) {
        ModelClass model = service.update(input, dbid);
        return createMappingJacksonValueForModelWithView(model, fullViewClass);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_AUTHORIZED)
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    MappingJacksonValue delete(@PathVariable int dbid) {
        ModelClass model = service.delete(dbid);
        return createMappingJacksonValueForModelWithView(model, fullViewClass);
    }

    protected MappingJacksonValue createMappingJacksonValueForModelWithView(ModelClass model, Class<? extends ConfigurationViews.V1> viewClass) {
        MappingJacksonValue mapper = new MappingJacksonValue(model);
        mapper.setSerializationView(viewClass);
        return mapper; 
    }

    protected Class<? extends ConfigurationViews.V1> getViewByDetailLevel(String detailLevel) {
        if ("brief".equals(detailLevel)) {
            return briefViewClass;
        }
        else if ("full".equals(detailLevel)) {
            return fullViewClass;
        }
        else {
            return idViewClass;
        }
    }
}
