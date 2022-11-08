package com.pokemonurpg.configuration.v1.lib.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedConfigurationRepository<T> extends ConfigurationRepository<T> {
    T findByName(String name);
    T findFirstByNameStartingWith(String name);
}
