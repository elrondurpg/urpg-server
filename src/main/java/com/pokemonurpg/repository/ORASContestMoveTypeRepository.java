package com.pokemonurpg.repository;

import com.pokemonurpg.object.ORASContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ORASContestMoveTypeRepository extends JpaRepository<ORASContestMoveType, Integer> {
    @Query("select a.name from ORASContestMoveType a")
    List<Object> findAllNames();
    ORASContestMoveType findByName(String name);
    ORASContestMoveType findByDbid(Integer dbid);
    List<ORASContestMoveType> findByNameStartingWith(String name);
}
