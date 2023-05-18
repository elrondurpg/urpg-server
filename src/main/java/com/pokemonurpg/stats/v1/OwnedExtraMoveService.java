package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

@Service
public class OwnedExtraMoveService {

    @Resource
    private AttackService attackService;

    public void updateAll(OwnedPokemonRequest input, OwnedPokemon pokemon) {
        Set<Attack> attacks = pokemon.getOwnedExtraMoves();
        if (attacks == null) {
            attacks = new HashSet<Attack>();
            pokemon.setOwnedExtraMoves(attacks);
        }

        for (OwnedExtraMoveRequest attack : input.getOwnedExtraMoves()) {
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
