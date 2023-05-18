package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAttack;
import com.pokemonurpg.entities.v1.PokemonAttackKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonAttackRepository extends JpaRepository<PokemonAttack, PokemonAttackKey> {
    List<PokemonAttack> findBySpecies(Pokemon pokemon);
    PokemonAttack findBySpeciesAndAttack(Pokemon pokemon, Attack attack);
}
