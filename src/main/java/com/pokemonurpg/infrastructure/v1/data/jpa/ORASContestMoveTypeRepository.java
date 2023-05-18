package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ORASContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ORASContestMoveTypeRepository extends JpaRepository<ORASContestMoveType, Integer> {
    @Query("select a.name from ORASContestMoveType a")
    List<String> findAllNames();
    ORASContestMoveType findByName(String name);
    ORASContestMoveType findByDbid(Integer dbid);
    ORASContestMoveType findFirstByNameStartingWith(String name);
}
