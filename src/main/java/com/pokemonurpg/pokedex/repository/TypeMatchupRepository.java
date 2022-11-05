package com.pokemonurpg.pokedex.repository;

import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.pokedex.models.TypeMatchup;
import com.pokemonurpg.pokedex.models.TypeMatchupKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatchupRepository extends JpaRepository<TypeMatchup, TypeMatchupKey> {
    TypeMatchup findByAttackTypeAndDefendType(Type attackType, Type defendType);
}
