package com.pokemonurpg.configuration.v1.lib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class ConfigurationV1Config {
    @Autowired
    void configureObjectMapper(final ObjectMapper mapper) {
        mapper.registerModule(new SimpleModule().addSerializer(PagedConfiguration.class, new PagedConfigurationSerializer()));
    }
}
