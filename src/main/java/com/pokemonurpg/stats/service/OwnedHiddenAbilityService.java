package com.pokemonurpg.stats.service;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.service.AbilityService;
import com.pokemonurpg.stats.input.OwnedHiddenAbilityInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class OwnedHiddenAbilityService {

    @Resource
    private AbilityService abilityService;

    public void updateAll(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        Set<Ability> abilities = pokemon.getOwnedHiddenAbilities();

        for (OwnedHiddenAbilityInputDto ability : input.getOwnedHiddenAbilities()) {
            String name = ability.getAbility();
            Ability abilityObject = abilityService.findByName(name);
            if (ability.getDelete()) {
                abilities.remove(abilityObject);
            }
            else
                abilities.add(abilityObject);
        }
    }
}
