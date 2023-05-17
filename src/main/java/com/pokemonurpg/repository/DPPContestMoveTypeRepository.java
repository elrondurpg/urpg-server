package com.pokemonurpg.repository;

import com.pokemonurpg.object.DPPContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DPPContestMoveTypeRepository extends JpaRepository<DPPContestMoveType, Integer> {
    @Query("select a.name from DPPContestMoveType a")
    List<Object> findAllNames();
    DPPContestMoveType findByName(String name);
    DPPContestMoveType findByDbid(Integer dbid);
    List<DPPContestMoveType> findByNameStartingWith(String name);
}
