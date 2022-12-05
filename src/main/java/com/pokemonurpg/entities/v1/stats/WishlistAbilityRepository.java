package com.pokemonurpg.entities.v1.stats;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.pokemon.Ability;

public interface WishlistAbilityRepository extends JpaRepository<WishlistAbility, WishlistAbilityKey> {
    Set<WishlistAbility> findByPokemon(OwnedPokemon pokemon);
    WishlistAbility findByPokemonAndAbility(OwnedPokemon pokemon, Ability ability);
}
