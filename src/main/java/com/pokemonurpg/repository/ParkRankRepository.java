package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.ParkRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRankRepository extends JpaRepository<ParkRank, Integer> {
    @Query("select x.name from ParkRank x")
    List<Object> findAllNames();
    ParkRank findByDbid(int dbid);
    ParkRank findByName(String name);
    List<ParkRank> findByNameStartingWith(String name);
}
