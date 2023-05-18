package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionSlotRepository extends JpaRepository<ChampionSlot, Integer> {
    @Query("select s.name from Champion s")
    List<String> findAllNames();
    ChampionSlot findByDbid(Integer dbid);
    ChampionSlot findByName(String name);
    ChampionSlot findFirstByNameStartingWith(String name);
    ChampionSlot findByCurrentOwnerRecord(ChampionRecord currentOwnerRecord);
    void deleteByDbid(int dbid);
}
