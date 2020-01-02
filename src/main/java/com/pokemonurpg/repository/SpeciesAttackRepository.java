package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.SpeciesAttack;
import com.pokemonurpg.object.pokemon.SpeciesAttackKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    Optional<SpeciesAttack> findById(SpeciesAttackKey key);

    List<SpeciesAttack> findByIdSpeciesDbid(int dbid);

    SpeciesAttack findByIdSpeciesDbidAndIdAttackDbid(int speciesDbid, int attackDbid);
}
