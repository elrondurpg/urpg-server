package com.pokemonurpg.configuration.v1.lib.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemonurpg.configuration.v1.lib.config.ConfigurationPageMapper;
import com.pokemonurpg.configuration.v1.lib.service.ConfigurationService;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

public abstract class ConfigurationController<
        ModelClass, 
        FilterableGetParamSubclass extends FilterableGetParams<ModelClass>, 
        ConfigurationServiceClass extends ConfigurationService<ModelClass, FilterableGetParamSubclass, ?>> {

    @Autowired
    protected ConfigurationServiceClass service;

    protected final Class<? extends ConfigurationViews.V1> idViewClass;
    protected final Class<? extends ConfigurationViews.V1> briefViewClass;
    protected final Class<? extends ConfigurationViews.V1> fullViewClass;
    protected final String resourceName;

    public ConfigurationController(ConfigControllerDefinition definition) {
        this.idViewClass = definition.getIdViewClass();
        this.briefViewClass = definition.getBriefViewClass();
        this.fullViewClass = definition.getFullViewClass();
        this.resourceName = definition.getResourceName();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    ConfigurationPageMapper find(@Valid FilterableGetParamSubclass params) throws JsonProcessingException {
        Page<ModelClass> page = service.find(params);
        Class<? extends ConfigurationViews.V1> view = getViewByDetailLevel(params.getDetailLevel());
        return new ConfigurationPageMapper(page, view);
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
