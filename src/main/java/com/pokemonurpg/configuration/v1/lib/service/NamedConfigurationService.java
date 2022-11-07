package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;

public class NamedConfigurationService <
        ModelClass, 
        FilterableGetParamSubclass extends FilterableGetParams<ModelClass>, 
        RepositorySubclass extends NamedConfigurationRepository<ModelClass>
    > 
    extends ConfigurationService<ModelClass, FilterableGetParamSubclass, RepositorySubclass> {

    public ModelClass findByName(String name) {
        ModelClass model = findByNameExact(name);
        if (model == null && name != null) {
            return repository.findFirstByNameStartingWith(name);
        }
        else return model;
    }

    public ModelClass findByNameExact(String name) {
        return repository.findByName(name);
    }
}
