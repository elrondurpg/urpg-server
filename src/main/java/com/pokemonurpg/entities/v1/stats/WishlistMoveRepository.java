package com.pokemonurpg.entities.v1.stats;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.attack.Attack;

public interface WishlistMoveRepository extends JpaRepository<WishlistMove, WishlistMoveKey> {
    Set<WishlistMove> findByPokemon(OwnedPokemon pokemon);
    WishlistMove findByPokemonAndMove(OwnedPokemon pokemon, Attack move);
}
