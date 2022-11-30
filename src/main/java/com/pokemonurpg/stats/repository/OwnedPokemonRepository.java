package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedPokemonRepository  extends JpaRepository<OwnedPokemon, Integer> {
    List<OwnedPokemon> findByTrainer(Member trainer);
    OwnedPokemon findByDbid(int dbid);
    void deleteByDbid(int dbid);
}
