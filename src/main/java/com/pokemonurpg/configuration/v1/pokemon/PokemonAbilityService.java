package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.infrastructure.v1.data.jpa.PokemonAbilityRepository;
import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.infrastructure.v1.data.jpa.AbilityRepository;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAbility;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class PokemonAbilityService {

    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;

    @Resource
    private AbilityRepository abilityRepository;

    public List<PokemonAbility> findBySpecies(Pokemon pokemon) {
        return pokemonAbilityRepository.findBySpecies(pokemon);
    }

    public void update(Pokemon pokemon, PokemonAbilityRequest input) {
        Ability ability = abilityRepository.findByName(input.getName());
        PokemonAbility existingRecord = pokemonAbilityRepository.findBySpeciesAndAbility(pokemon, ability);
        if (existingRecord != null) {
            if (input.getDelete()) {
                pokemonAbilityRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                pokemonAbilityRepository.save(existingRecord);
            }
        }
        else {
            PokemonAbility newRecord = new PokemonAbility(input, pokemon, ability);
            pokemonAbilityRepository.save(newRecord);
        }
    }
}
