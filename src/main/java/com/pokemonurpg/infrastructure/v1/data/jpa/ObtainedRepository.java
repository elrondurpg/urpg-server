package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Obtained;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObtainedRepository extends JpaRepository<Obtained, Integer> {
    @Query("select t.name from Obtained t")
    List<String> findAllNames();
    Obtained findByDbid(int dbid);
    Obtained findByName(String name);
    Obtained findFirstByNameStartingWith(String name);
}
