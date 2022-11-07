package com.pokemonurpg.configuration.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.lib.input.v1.FilterableGetParams;

public abstract class ConfigurationService<ModelClass, FilterableGetParamSubclass extends FilterableGetParams<ModelClass>, RepositorySubclass extends JpaRepository<ModelClass, ?>> {

    @Autowired
    protected RepositorySubclass repository;

    public Page<ModelClass> find(FilterableGetParamSubclass params) {
        return repository.findAll(params.asExample(), params.asPageRequest());
    }
}
