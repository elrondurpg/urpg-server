package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ContestAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestAttributeRepository extends JpaRepository<ContestAttribute, Integer> {
    @Query("select a.name from ContestAttribute a")
    List<String> findAllNames();
    ContestAttribute findByName(String name);
    ContestAttribute findByDbid(Integer dbid);
    ContestAttribute findFirstByNameStartingWith(String name);
}
