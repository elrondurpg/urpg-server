package com.pokemonurpg.configuration.v1.lib.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IndexedConfigurationRepository<T> extends ConfigurationRepository<T, Integer> {
    T findByDbid(Integer dbid);
}
