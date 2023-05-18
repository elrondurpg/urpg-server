package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.infrastructure.v1.data.jpa.PokemonAttackRepository;
import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAttack;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class PokemonAttackService {

    @Resource
    private PokemonAttackRepository pokemonAttackRepository;

    @Resource
    private AttackRepository attackRepository;

    public List<PokemonAttack> findBySpecies(Pokemon pokemon) {
        return pokemonAttackRepository.findBySpecies(pokemon);
    }

    public void update(Pokemon pokemon, PokemonAttackRequest input) {
        Attack attack = attackRepository.findByName(input.getName());
        PokemonAttack existingRecord = pokemonAttackRepository.findBySpeciesAndAttack(pokemon, attack);
        if (existingRecord != null) {
            if (input.getDelete()) {
                pokemonAttackRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                pokemonAttackRepository.save(existingRecord);
            }
        }
        else {
            PokemonAttack newRecord = new PokemonAttack(input, pokemon, attack);
            pokemonAttackRepository.save(newRecord);
        }
    }
}
