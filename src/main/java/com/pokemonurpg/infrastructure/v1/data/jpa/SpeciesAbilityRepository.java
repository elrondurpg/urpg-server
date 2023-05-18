package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAbility;
import com.pokemonurpg.entities.v1.SpeciesAbilityKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    List<SpeciesAbility> findBySpecies(Species species);
    SpeciesAbility findBySpeciesAndAbility(Species species, Ability ability);
}
