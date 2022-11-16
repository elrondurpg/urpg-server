package com.pokemonurpg.configuration.v1.contest.rank.repository;

import com.pokemonurpg.configuration.v1.contest.models.ContestRank;
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
