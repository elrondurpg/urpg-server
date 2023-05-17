package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.KnownChampion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownChampionRepository extends JpaRepository<KnownChampion, String> {
    @Query("select s.name from KnownChampion s")
    List<String> findAllNames();
    KnownChampion findByDbid(int dbid);
    KnownChampion findByName(String name);
    KnownChampion findFirstByNameStartingWith(String name);
}
