package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v3.pokemon.ability.AbilityEntity;

public interface SpeciesAbilityJpaRepository extends JpaRepository<SpeciesAbilityEntity, SpeciesAbilityKeyEntity> {
    List<SpeciesAbilityEntity> findBySpecies(SpeciesEntity species);
    SpeciesAbilityEntity findBySpeciesAndAbility(SpeciesEntity species, AbilityEntity ability);
}
