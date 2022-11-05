package com.pokemonurpg.configuration.v1.pokemon.species.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesAbilityRepository;

import java.util.List;

import javax.annotation.Resource;

@Service
public class SpeciesAbilityService {

    @Resource
    private SpeciesAbilityRepository speciesAbilityRepository;

    @Resource
    private AbilityRepository abilityRepository;

    public List<SpeciesAbility> findBySpecies(Species species) {
        return speciesAbilityRepository.findBySpecies(species);
    }

    public void update(Species species, SpeciesAbilityInputDto input) {
        Ability ability = abilityRepository.findByName(input.getName());
        SpeciesAbility existingRecord = speciesAbilityRepository.findBySpeciesAndAbility(species, ability);
        if (existingRecord != null) {
            if (input.getDelete()) {
                speciesAbilityRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                speciesAbilityRepository.save(existingRecord);
            }
        }
        else {
            SpeciesAbility newRecord = new SpeciesAbility(input, species, ability);
            speciesAbilityRepository.save(newRecord);
        }
    }
}
