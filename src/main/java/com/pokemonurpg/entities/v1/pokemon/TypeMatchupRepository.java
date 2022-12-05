package com.pokemonurpg.entities.v1.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatchupRepository extends JpaRepository<TypeMatchup, TypeMatchupKey> {
    TypeMatchup findByAttackTypeAndDefendType(Type attackType, Type defendType);
}
