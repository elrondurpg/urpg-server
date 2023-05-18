package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Nature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NatureRepository extends JpaRepository<Nature, Integer> {
    @Query("select t.name from Nature t")
    List<String> findAllNames();
    Nature findByDbid(int dbid);
    Nature findByName(String name);
    Nature findFirstByNameStartingWith(String name);
}
