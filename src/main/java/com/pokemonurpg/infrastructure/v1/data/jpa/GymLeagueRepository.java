package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.GymLeague;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymLeagueRepository extends JpaRepository<GymLeague, Integer> {
    @Query("select t.name from GymLeague t")
    List<String> findAllNames();
    GymLeague findByDbid(int dbid);
    GymLeague findByName(String name);
    GymLeague findFirstByNameStartingWith(String name);
}
