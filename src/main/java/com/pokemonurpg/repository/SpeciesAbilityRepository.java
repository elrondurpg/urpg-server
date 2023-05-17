package com.pokemonurpg.repository;

import com.pokemonurpg.object.SpeciesAbility;
import com.pokemonurpg.object.SpeciesAbilityKey;
import com.pokemonurpg.object.SpeciesAttack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SpeciesAbilityRepository extends JpaRepository<SpeciesAbility, SpeciesAbilityKey> {
    Optional<SpeciesAbility> findById(SpeciesAbilityKey key);
    List<SpeciesAbility> findByIdSpeciesDbid(int dbid);
    SpeciesAbility findByIdSpeciesDbidAndIdAbilityDbid(int speciesDbid, int abilityDbid);
}
