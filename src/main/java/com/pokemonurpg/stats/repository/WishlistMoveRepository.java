package com.pokemonurpg.stats.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.models.WishlistMove;
import com.pokemonurpg.stats.models.WishlistMoveKey;

public interface WishlistMoveRepository extends JpaRepository<WishlistMove, WishlistMoveKey> {
    Set<WishlistMove> findByPokemon(OwnedPokemon pokemon);
    WishlistMove findByPokemonAndMove(OwnedPokemon pokemon, Attack move);
}
