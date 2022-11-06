package com.pokemonurpg.configuration.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemonurpg.configuration.v1.config.ConfigurationPageMapper;
import com.pokemonurpg.configuration.v1.service.ConfigurationService;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.lib.v1.FilterableGetParams;
import com.pokemonurpg.security.annotation.AllowAll;

public abstract class ConfigurationController<
        ModelClass, 
        FilterableGetParamSubclass extends FilterableGetParams<ModelClass>, 
        ConfigurationServiceClass extends ConfigurationService<ModelClass, FilterableGetParamSubclass, ?>> {

    @Autowired
    protected ConfigurationServiceClass service;

    protected final Class<? extends ConfigurationViews.V1> idViewClass;
    protected final Class<? extends ConfigurationViews.V1> briefViewClass;
    protected final Class<? extends ConfigurationViews.V1> fullViewClass;

    public ConfigurationController(
        Class<? extends ConfigurationViews.V1> idViewClass, 
        Class<? extends ConfigurationViews.V1> briefViewClass, 
        Class<? extends ConfigurationViews.V1> fullViewClass) {
        this.idViewClass = idViewClass;
        this.briefViewClass = briefViewClass;
        this.fullViewClass = fullViewClass;
    }

    @AllowAll
    @GetMapping
    public @ResponseBody
    ConfigurationPageMapper find(@Valid FilterableGetParamSubclass params) throws JsonProcessingException {
        Page<ModelClass> page = service.find(params);
        Class<? extends ConfigurationViews.V1> view = getViewByDetailLevel(params.getDetailLevel());
        return new ConfigurationPageMapper(page, view);
    }

    private Class<? extends ConfigurationViews.V1> getViewByDetailLevel(String detailLevel) {
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
