package com.pokemonurpg.stats.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.models.WishlistAbility;
import com.pokemonurpg.stats.models.WishlistAbilityKey;

public interface WishlistAbilityRepository extends JpaRepository<WishlistAbility, WishlistAbilityKey> {
    Set<WishlistAbility> findByPokemon(OwnedPokemon pokemon);
    WishlistAbility findByPokemonAndAbility(OwnedPokemon pokemon, Ability ability);
}
