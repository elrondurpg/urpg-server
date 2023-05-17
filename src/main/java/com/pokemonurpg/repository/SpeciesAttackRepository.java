package com.pokemonurpg.repository;

import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.object.SpeciesAttackKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    Optional<SpeciesAttack> findById(SpeciesAttackKey key);

    List<SpeciesAttack> findByIdSpeciesDbid(int dbid);

    SpeciesAttack findByIdSpeciesDbidAndIdAttackDbid(int speciesDbid, int attackDbid);
}
