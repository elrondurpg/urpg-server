package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Ability;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.entities.SpeciesAbility;
import com.pokemonurpg.entities.SpeciesAbilityKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    List<SpeciesAbility> findBySpecies(Species species);
    SpeciesAbility findBySpeciesAndAbility(Species species, Ability ability);
}
