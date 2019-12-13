package com.pokemonurpg.repository;

import com.pokemonurpg.object.OwnedPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedPokemonRepository  extends JpaRepository<OwnedPokemon, Integer> {
    OwnedPokemon findByDbid(int dbid);
}
