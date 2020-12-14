package com.pokemonurpg.stats.repository;

import com.pokemonurpg.stats.models.OwnedPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedPokemonRepository  extends JpaRepository<OwnedPokemon, Integer> {
    OwnedPokemon findByDbid(int dbid);
}
