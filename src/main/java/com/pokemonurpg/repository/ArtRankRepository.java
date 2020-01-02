package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.ArtRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtRankRepository extends JpaRepository<ArtRank, Integer> {
    @Query("select x.name from ArtRank x")
    List<Object> findAllNames();
    ArtRank findByDbid(int dbid);
    ArtRank findByName(String name);
    List<ArtRank> findByNameStartingWith(String name);
}
