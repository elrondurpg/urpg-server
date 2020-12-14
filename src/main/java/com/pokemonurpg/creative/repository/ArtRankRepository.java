package com.pokemonurpg.creative.repository;

import com.pokemonurpg.creative.models.ArtRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtRankRepository extends JpaRepository<ArtRank, Integer> {
    @Query("select a.name from ArtRank a")
    List<String> findAllNames();
    ArtRank findByName(String name);
    ArtRank findByDbid(Integer dbid);
    ArtRank findFirstByNameStartingWith(String name);
}
