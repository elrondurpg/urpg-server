package com.pokemonurpg.infrastructure.v1.data.jpa;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.entities.v1.WishlistAbility;
import com.pokemonurpg.entities.v1.WishlistAbilityKey;

public interface WishlistAbilityRepository extends JpaRepository<WishlistAbility, WishlistAbilityKey> {
    Set<WishlistAbility> findByPokemon(OwnedPokemon pokemon);
    WishlistAbility findByPokemonAndAbility(OwnedPokemon pokemon, Ability ability);
}
