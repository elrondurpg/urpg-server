package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.attack.AttackRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.SpeciesAttack;
import com.pokemonurpg.entities.v1.pokemon.SpeciesAttackRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class SpeciesAttackService {

    @Resource
    private SpeciesAttackRepository speciesAttackRepository;

    @Resource
    private AttackRepository attackRepository;

    public List<SpeciesAttack> findBySpecies(Species species) {
        return speciesAttackRepository.findBySpecies(species);
    }

    public void update(Species species, SpeciesAttackInputDto input) {
        Attack attack = attackRepository.findByName(input.getName());
        SpeciesAttack existingRecord = speciesAttackRepository.findBySpeciesAndAttack(species, attack);
        if (existingRecord != null) {
            if (input.getDelete()) {
                delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                speciesAttackRepository.save(existingRecord);
            }
        }
        else {
            SpeciesAttack newRecord = new SpeciesAttack(input, species, attack);
            speciesAttackRepository.save(newRecord);
        }
    }

    public void delete(SpeciesAttack speciesAttack) {
        speciesAttackRepository.delete(speciesAttack);
    }
}
