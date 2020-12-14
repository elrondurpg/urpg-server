package com.pokemonurpg.contest.repository;

import com.pokemonurpg.contest.models.ContestAttribute;
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
