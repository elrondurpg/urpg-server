package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v3.attack.AttackEntity;

public interface SpeciesAttackJpaRepository  extends JpaRepository<SpeciesAttackEntity, SpeciesAttackKeyEntity> {
    List<SpeciesAttackEntity> findBySpecies(SpeciesEntity species);
    SpeciesAttackEntity findBySpeciesAndAttack(SpeciesEntity species, AttackEntity attack);
}
