package com.pokemonurpg.contest.repository;

import com.pokemonurpg.contest.models.ORASContestMoveType;
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
