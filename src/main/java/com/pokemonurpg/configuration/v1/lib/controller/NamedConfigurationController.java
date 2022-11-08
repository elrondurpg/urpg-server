package com.pokemonurpg.configuration.v1.lib.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

public abstract class NamedConfigurationController<
        ModelClass extends ConfigurationModel, 
        FilterableGetParamsClass extends FilterableGetParams<ModelClass>,
        InputDtoClass extends NamedConfigurationInputDto
    > 
    extends ConfigurationController <ModelClass, FilterableGetParamsClass, InputDtoClass> {

    protected NamedConfigurationService<ModelClass, InputDtoClass> service;

    public NamedConfigurationController(ConfigControllerDefinition definition, NamedConfigurationService<ModelClass, InputDtoClass> service) {
        super(definition, service);
        this.service = service;
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    MappingJacksonValue findByName(@PathVariable("name") String name) {
        ModelClass model = service.findByName(name);
        return createMappingJacksonValueForModelWithView(model, fullViewClass);
    }
}
