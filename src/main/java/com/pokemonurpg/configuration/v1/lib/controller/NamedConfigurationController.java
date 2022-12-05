package com.pokemonurpg.configuration.v1.lib.controller;

import javax.validation.Valid;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public abstract class NamedConfigurationController<
        ModelClass extends NamedEntity, 
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
}
