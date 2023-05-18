package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAbility;
import com.pokemonurpg.entities.v1.PokemonAbilityKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonAbilityRepository extends JpaRepository<PokemonAbility, PokemonAbilityKey> {
    List<PokemonAbility> findBySpecies(Pokemon pokemon);
    PokemonAbility findBySpeciesAndAbility(Pokemon pokemon, Ability ability);
}
