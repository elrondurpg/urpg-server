package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.BattleGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattleGenerationRepository extends JpaRepository<BattleGeneration, Integer> {
    @Query("select t.name from BattleGeneration t")
    List<Object> findAllNames();
    BattleGeneration findByDbid(int dbid);
    BattleGeneration findByName(String name);
    List<BattleGeneration> findByNameStartingWith(String name);
}
