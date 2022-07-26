package com.pokemonurpg.species.repository;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAbility;
import com.pokemonurpg.species.models.SpeciesAbilityKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    List<SpeciesAbility> findBySpecies(Species species);
    SpeciesAbility findBySpeciesAndAbility(Species species, Ability ability);
}
