package com.pokemonurpg.entities.v1.pokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.attack.Attack;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    List<SpeciesAttack> findBySpecies(Species species);
    SpeciesAttack findBySpeciesAndAttack(Species species, Attack attack);
}
