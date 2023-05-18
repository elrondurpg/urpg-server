package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.KnownChampion;
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
