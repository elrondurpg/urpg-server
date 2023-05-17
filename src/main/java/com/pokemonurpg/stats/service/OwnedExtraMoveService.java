package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.stats.input.OwnedExtraMoveInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

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