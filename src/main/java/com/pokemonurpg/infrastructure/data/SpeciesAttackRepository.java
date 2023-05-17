package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.entities.SpeciesAttack;
import com.pokemonurpg.entities.SpeciesAttackKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    List<SpeciesAttack> findBySpecies(Species species);
    SpeciesAttack findBySpeciesAndAttack(Species species, Attack attack);
}
