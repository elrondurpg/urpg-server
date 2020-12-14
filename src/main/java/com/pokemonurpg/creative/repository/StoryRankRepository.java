package com.pokemonurpg.creative.repository;

import com.pokemonurpg.creative.models.StoryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRankRepository extends JpaRepository<StoryRank, Integer> {
    @Query("select a.name from StoryRank a")
    List<String> findAllNames();
    StoryRank findByName(String name);
    StoryRank findByDbid(Integer dbid);
    StoryRank findFirstByNameStartingWith(String name);
}
