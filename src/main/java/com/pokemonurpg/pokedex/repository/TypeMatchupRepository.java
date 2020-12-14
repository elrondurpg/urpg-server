package com.pokemonurpg.pokedex.repository;

import com.pokemonurpg.pokedex.models.TypeMatchup;
import com.pokemonurpg.pokedex.models.TypeMatchupKey;
import com.pokemonurpg.species.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatchupRepository extends JpaRepository<TypeMatchup, TypeMatchupKey> {
    TypeMatchup findByAttackTypeAndDefendType(Type attackType, Type defendType);
}
