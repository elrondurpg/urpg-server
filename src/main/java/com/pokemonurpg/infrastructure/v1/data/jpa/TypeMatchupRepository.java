package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.TypeMatchup;
import com.pokemonurpg.entities.v1.TypeMatchupKey;
import com.pokemonurpg.entities.v1.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatchupRepository extends JpaRepository<TypeMatchup, TypeMatchupKey> {
    TypeMatchup findByAttackTypeAndDefendType(Type attackType, Type defendType);
}
