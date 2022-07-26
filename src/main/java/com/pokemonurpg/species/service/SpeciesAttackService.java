package com.pokemonurpg.species.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAttack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.species.repository.SpeciesAttackRepository;
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
                speciesAttackRepository.delete(existingRecord);
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
}
