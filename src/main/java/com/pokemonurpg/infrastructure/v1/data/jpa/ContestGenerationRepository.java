package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ContestGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestGenerationRepository extends JpaRepository<ContestGeneration, Integer> {
    @Query("select a.name from ContestType a")
    List<String> findAllNames();
    ContestGeneration findByName(String name);
    ContestGeneration findByDbid(Integer dbid);
    ContestGeneration findFirstByNameStartingWith(String name);
}
