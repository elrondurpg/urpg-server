package com.pokemonurpg.infrastructure.v1.data.jpa;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.entities.v1.WishlistMove;
import com.pokemonurpg.entities.v1.WishlistMoveKey;

public interface WishlistMoveRepository extends JpaRepository<WishlistMove, WishlistMoveKey> {
    Set<WishlistMove> findByPokemon(OwnedPokemon pokemon);
    WishlistMove findByPokemonAndMove(OwnedPokemon pokemon, Attack move);
}
