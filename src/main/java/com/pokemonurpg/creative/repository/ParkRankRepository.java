package com.pokemonurpg.creative.repository;

import com.pokemonurpg.creative.models.ParkRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRankRepository extends JpaRepository<ParkRank, Integer> {
    @Query("select a.name from ParkRank a")
    List<String> findAllNames();
    ParkRank findByName(String name);
    ParkRank findByDbid(Integer dbid);
    ParkRank findFirstByNameStartingWith(String name);
}
