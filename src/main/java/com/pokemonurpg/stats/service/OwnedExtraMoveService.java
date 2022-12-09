package com.pokemonurpg.stats.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.attack.attack.service.AttackService;
import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.stats.input.OwnedExtraMoveInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;

@Service
public class OwnedExtraMoveService {

    @Resource
    private AttackService attackService;

    public void updateAll(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        Set<Attack> attacks = pokemon.getOwnedExtraMoves();
        if (attacks == null) {
            attacks = new HashSet<Attack>();
            pokemon.setOwnedExtraMoves(attacks);
        }

        for (OwnedExtraMoveInputDto attack : input.getOwnedExtraMoves()) {
            String name = attack.getAttack();
            Attack attackObject = attackService.findByName(name);
            if (attack.getDelete()) {
                attacks.remove(attackObject);
            }
            else
                attacks.add(attackObject);
        }
    }
}
