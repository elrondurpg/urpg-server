package com.pokemonurpg.entities.v1.pokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    List<SpeciesAbility> findBySpecies(Species species);
    SpeciesAbility findBySpeciesAndAbility(Species species, Ability ability);
}
