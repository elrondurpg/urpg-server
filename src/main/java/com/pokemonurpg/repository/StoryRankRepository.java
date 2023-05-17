package com.pokemonurpg.repository;

import com.pokemonurpg.object.StoryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoryRankRepository extends JpaRepository<StoryRank, Integer> {
    @Query("select x.name from StoryRank x")
    List<Object> findAllNames();
    StoryRank findByDbid(int dbid);
    StoryRank findByName(String name);
    List<StoryRank> findByNameStartingWith(String name);
}
