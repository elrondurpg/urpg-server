package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.infrastructure.v1.data.jpa.SpeciesAttackRepository;
import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAttack;
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
