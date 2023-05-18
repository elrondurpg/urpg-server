package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedPokemonRepository  extends JpaRepository<OwnedPokemon, Integer> {
    List<OwnedPokemon> findByTrainer(Member trainer);
    OwnedPokemon findByDbid(Integer dbid);
    void deleteByDbid(int dbid);
}
