package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.SpeciesAbility;
import com.pokemonurpg.object.pokemon.SpeciesAbilityKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    Optional<SpeciesAbility> findById(SpeciesAbilityKey key);
    List<SpeciesAbility> findByIdSpeciesDbid(int dbid);
    SpeciesAbility findByIdSpeciesDbidAndIdAbilityDbid(int speciesDbid, int abilityDbid);
}
