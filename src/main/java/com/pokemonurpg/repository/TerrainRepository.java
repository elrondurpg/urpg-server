package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TerrainRepository extends JpaRepository<Terrain, Integer> {
    @Query("select t.name from Terrain t")
    List<Object> findAllNames();
    Terrain findByDbid(int dbid);
    Terrain findByName(String name);
    List<Terrain> findByNameStartingWith(String name);
}
