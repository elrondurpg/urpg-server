package com.pokemonurpg.configuration.v1.contest.attribute.repository;

import com.pokemonurpg.configuration.v1.contest.models.ContestAttribute;
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
