package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Query("select t.name from Section t")
    List<String> findAllNames();
    Section findByDbid(int dbid);
    Section findByName(String name);
    Section findFirstByNameStartingWith(String name);
}
