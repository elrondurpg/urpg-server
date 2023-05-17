package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.AdvContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvContestMoveTypeRepository extends JpaRepository<AdvContestMoveType, Integer> {
    @Query("select a.name from AdvContestMoveType a")
    List<String> findAllNames();
    AdvContestMoveType findByName(String name);
    AdvContestMoveType findByDbid(Integer dbid);
    AdvContestMoveType findFirstByNameStartingWith(String name);
}

