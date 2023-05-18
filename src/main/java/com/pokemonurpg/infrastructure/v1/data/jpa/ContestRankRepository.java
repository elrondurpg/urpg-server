package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ContestRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestRankRepository extends JpaRepository<ContestRank, Integer> {
    @Query("select a.name from ContestRank a")
    List<String> findAllNames();
    ContestRank findByName(String name);
    ContestRank findByDbid(Integer dbid);
    ContestRank findFirstByNameStartingWith(String name);
}
