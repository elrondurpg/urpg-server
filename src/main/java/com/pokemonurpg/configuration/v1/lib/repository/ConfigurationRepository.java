package com.pokemonurpg.configuration.v1.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ConfigurationRepository<T> extends JpaRepository<T, Integer> {
    T findByDbid(Integer dbid);
}
