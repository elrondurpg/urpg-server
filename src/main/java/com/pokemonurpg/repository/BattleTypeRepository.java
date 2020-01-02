package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.BattleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattleTypeRepository extends JpaRepository<BattleType, Integer> {
    @Query("select t.name from BattleType t")
    List<Object> findAllNames();
    BattleType findByDbid(int dbid);
    BattleType findByName(String name);
    List<BattleType> findByNameStartingWith(String name);
}
