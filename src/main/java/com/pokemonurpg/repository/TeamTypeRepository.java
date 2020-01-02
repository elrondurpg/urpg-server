package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamTypeRepository extends JpaRepository<TeamType, Integer> {
    @Query("select t.name from TeamType t")
    List<Object> findAllNames();
    TeamType findByDbid(int dbid);
    TeamType findByName(String name);
    List<TeamType> findByNameStartingWith(String name);
}
