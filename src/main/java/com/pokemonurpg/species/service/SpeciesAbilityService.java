package com.pokemonurpg.species.service;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.repository.AbilityRepository;
import com.pokemonurpg.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAbility;
import com.pokemonurpg.species.repository.SpeciesAbilityRepository;
import org.springframework.stereotype.Service;

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
