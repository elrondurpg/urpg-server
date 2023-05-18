package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.infrastructure.v1.data.jpa.SpeciesAbilityRepository;
import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.infrastructure.v1.data.jpa.AbilityRepository;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAbility;
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
