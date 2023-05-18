package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAttack;
import com.pokemonurpg.entities.v1.SpeciesAttackKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    List<SpeciesAttack> findBySpecies(Species species);
    SpeciesAttack findBySpeciesAndAttack(Species species, Attack attack);
}
