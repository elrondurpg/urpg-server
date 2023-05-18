package com.pokemonurpg.stats.v1;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.infrastructure.v1.data.jpa.AbilityRepository;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.entities.v1.WishlistAbility;
import com.pokemonurpg.infrastructure.v1.data.jpa.WishlistAbilityRepository;

@Service
public class WishlistAbilityService {

    @Resource
    private WishlistAbilityRepository wishlistAbilityRepository;

    @Resource
    private AbilityRepository abilityRepository;

    public Set<WishlistAbility> findByPokemon(OwnedPokemon pokemon) {
        return wishlistAbilityRepository.findByPokemon(pokemon);
    }

    public void update(WishlistAbilityRequest input, OwnedPokemon pokemon) {
        Ability ability = abilityRepository.findByName(input.getAbility());
        WishlistAbility existingRecord = wishlistAbilityRepository.findByPokemonAndAbility(pokemon, ability);

        if (existingRecord != null) {
            if (input.getDelete()) {
                wishlistAbilityRepository.delete(existingRecord);
            }
        }
        else {
            if (!pokemon.getOwnedHiddenAbilities().contains(ability)) {
                WishlistAbility newRecord = new WishlistAbility(pokemon, ability);
                wishlistAbilityRepository.save(newRecord);
            }
        }
    }
    
}
