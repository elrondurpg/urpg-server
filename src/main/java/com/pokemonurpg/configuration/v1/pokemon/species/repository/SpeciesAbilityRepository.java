package com.pokemonurpg.configuration.v1.pokemon.species.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbilityKey;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    List<SpeciesAbility> findBySpecies(Species species);
    SpeciesAbility findBySpeciesAndAbility(Species species, Ability ability);
}
