package com.pokemonurpg.configuration.v1.pokemon.ability.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesAbilityService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityService extends NamedConfigurationService<Ability, AbilityInputDto> {

    @Resource
    private SpeciesAbilityService speciesAbilityService;

    @Autowired
    public AbilityService(AbilityRepository abilityRepository) {
        super(abilityRepository);
    }

    @Override
    protected Ability createBase(AbilityInputDto input) {
        Ability ability = new Ability();
        updateBase(ability, input);
        return ability;
    }

    @Override
    protected void updateBase(Ability ability, AbilityInputDto input) {
        ability.setName(input.getName());
        ability.setDescription(input.getDescription());
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
