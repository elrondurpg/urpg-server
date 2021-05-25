package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.KnownChampion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownChampionRepository extends JpaRepository<KnownChampion, String> {
    @Query("select s.name from KnownChampion s")
    List<String> findAllNames();
    KnownChampion findByName(String name);
    KnownChampion findFirstByNameStartingWith(String name);
}
