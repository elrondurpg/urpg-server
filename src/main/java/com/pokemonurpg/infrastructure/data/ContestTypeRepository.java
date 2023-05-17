package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.ContestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestTypeRepository extends JpaRepository<ContestType, Integer> {
    @Query("select a.name from ContestType a")
    List<String> findAllNames();
    ContestType findByName(String name);
    ContestType findByDbid(Integer dbid);
    ContestType findFirstByNameStartingWith(String name);
}