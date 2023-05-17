package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Champion;
import com.pokemonurpg.entities.ChampionOwnershipTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionRepository extends JpaRepository<Champion, Integer> {
    @Query("select s.name from Champion s")
    List<String> findAllNames();
    Champion findByDbid(Integer dbid);
    Champion findByName(String name);
    Champion findFirstByNameStartingWith(String name);
    Champion findByCurrentOwnerRecord(ChampionOwnershipTerm currentOwnerRecord);
    void deleteByDbid(int dbid);
}
