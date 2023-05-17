package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.DPPContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DPPContestMoveTypeRepository extends JpaRepository<DPPContestMoveType, Integer> {
    @Query("select a.name from DPPContestMoveType a")
    List<String> findAllNames();
    DPPContestMoveType findByName(String name);
    DPPContestMoveType findByDbid(Integer dbid);
    DPPContestMoveType findFirstByNameStartingWith(String name);
}
