package com.pokemonurpg.configuration.v1.lib.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

public abstract class NamedConfigurationController<
        ModelClass, 
        FilterableGetParamSubclass extends FilterableGetParams<ModelClass>, 
        ConfigurationServiceClass extends NamedConfigurationService<ModelClass, FilterableGetParamSubclass, ?>
    > 
    extends ConfigurationController <ModelClass, FilterableGetParamSubclass, ConfigurationServiceClass> {

    public NamedConfigurationController(ConfigControllerDefinition definition) {
        super(definition);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    MappingJacksonValue findByName(@PathVariable("name") String name) {
        ModelClass model = service.findByName(name);
        MappingJacksonValue mapper = new MappingJacksonValue(model);
        mapper.setSerializationView(fullViewClass);
        return mapper;
    }
}
