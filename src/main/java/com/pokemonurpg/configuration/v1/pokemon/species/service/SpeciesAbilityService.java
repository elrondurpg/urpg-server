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
    private SpeciesAbilityRepository repository;

    @Resource
    private AbilityRepository abilityRepository;

    public List<SpeciesAbility> findBySpecies(Species species) {
        return repository.findBySpecies(species);
    }

    public void update(Species species, SpeciesAbilityInputDto input) {
        Ability ability = abilityRepository.findByName(input.getName());
        SpeciesAbility existingRecord = repository.findBySpeciesAndAbility(species, ability);
        if (existingRecord != null) {
            if (input.getDelete()) {
                repository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                repository.save(existingRecord);
            }
        }
        else {
            SpeciesAbility newRecord = new SpeciesAbility(input, species, ability);
            repository.save(newRecord);
        }
    }

    public void delete(SpeciesAbility record) {
        repository.delete(record);
    }
}
