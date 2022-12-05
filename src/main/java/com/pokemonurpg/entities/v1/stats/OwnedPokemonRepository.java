package com.pokemonurpg.entities.v1.stats;

import java.util.List;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedRepository;

public interface OwnedPokemonRepository  extends IndexedRepository<OwnedPokemon> {
    List<OwnedPokemon> findByTrainer(Member trainer);
}
