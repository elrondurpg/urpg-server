package com.pokemonurpg.repository;

import com.pokemonurpg.object.ContestAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestAttributeRepository extends JpaRepository<ContestAttribute, Integer> {
    @Query("select a.name from ContestAttribute a")
    List<Object> findAllNames();
    ContestAttribute findByName(String name);
    ContestAttribute findByDbid(Integer dbid);
    List<ContestAttribute> findByNameStartingWith(String name);
}
