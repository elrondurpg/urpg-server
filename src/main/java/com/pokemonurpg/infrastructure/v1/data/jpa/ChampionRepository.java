package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionRepository extends JpaRepository<Champion, String> {
    @Query("select s.name from KnownChampion s")
    List<String> findAllNames();
    Champion findByDbid(int dbid);
    Champion findByName(String name);
    Champion findFirstByNameStartingWith(String name);
}
