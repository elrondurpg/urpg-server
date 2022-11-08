package com.pokemonurpg.configuration.v1.lib.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

@Service
public class NamedConfigurationServiceFactory {

    @Resource
    private AbilityService abilityService;

    @Resource
    private SpeciesService speciesService;

    public NamedConfigurationService<?, ?> getServiceForClass(Class<?> type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "Ability": return abilityService;
                case "Species": return speciesService;
            }
        }
        return null;
    }
}
