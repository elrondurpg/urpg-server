package com.pokemonurpg.repository;

import com.pokemonurpg.object.StoryRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoryRankRepository extends JpaRepository<StoryRank, Integer> {
    Optional<StoryRank> findByDbid(int dbid);
    Optional<StoryRank> findByName(String name);
    List<StoryRank> findByNameStartingWith(String name);
}