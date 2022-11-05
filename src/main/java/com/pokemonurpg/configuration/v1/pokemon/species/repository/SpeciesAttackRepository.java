package com.pokemonurpg.configuration.v1.pokemon.species.repository;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttackKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    List<SpeciesAttack> findBySpecies(Species species);
    SpeciesAttack findBySpeciesAndAttack(Species species, Attack attack);
}
