package com.pokemonurpg.configuration.v1.pokemon.ability.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesAbilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityService extends NamedConfigurationService<Ability, AbilityInputDto> {

    private SpeciesAbilityService speciesAbilityService;

    @Autowired
    public AbilityService(AbilityRepository abilityRepository, SpeciesAbilityService speciesAbilityService) {
        super(abilityRepository);
        this.speciesAbilityService = speciesAbilityService;
    }

    @Override
    protected Ability createBase(AbilityInputDto input) {
        Ability ability = new Ability();
        updateBase(ability, input);
        return ability;
    }

    @Override
    protected void updateBase(Ability ability, AbilityInputDto input) {
        setIfNotNull(input.getName(), ability::setName);
        setIfNotNull(input.getDescription(), ability::setDescription);
    }

    @Override
    protected void updateEmbeddedValues(Ability model, AbilityInputDto input) { }

    @Override
    protected void updateAssociatedValues(Ability model, AbilityInputDto input) { }

    @Override
    protected void deleteAssociatedValues(Ability model) {
        model.getPokemon().forEach(record -> speciesAbilityService.delete(record));
    }

}
