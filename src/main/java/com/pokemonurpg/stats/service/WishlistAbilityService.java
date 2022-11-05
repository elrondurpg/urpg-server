package com.pokemonurpg.stats.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.stats.input.WishlistAbilityInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.models.WishlistAbility;
import com.pokemonurpg.stats.repository.WishlistAbilityRepository;

@Service
public class WishlistAbilityService {

    @Resource
    private WishlistAbilityRepository wishlistAbilityRepository;

    @Resource
    private AbilityRepository abilityRepository;

    public Set<WishlistAbility> findByPokemon(OwnedPokemon pokemon) {
        return wishlistAbilityRepository.findByPokemon(pokemon);
    }

    public void update(WishlistAbilityInputDto input, OwnedPokemon pokemon) {
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
