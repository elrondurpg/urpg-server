package com.pokemonurpg.configuration.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    protected abstract Class<? extends ConfigurationViews.V1> getIdViewClass();
    protected abstract Class<? extends ConfigurationViews.V1> getBriefViewClass();
    protected abstract Class<? extends ConfigurationViews.V1> getFullViewClass();

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
            return getBriefViewClass();
        }
        else if ("full".equals(detailLevel)) {
            return getFullViewClass();
        }
        else {
            return getIdViewClass();
        }
    }
}
