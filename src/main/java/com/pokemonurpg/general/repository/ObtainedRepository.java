package com.pokemonurpg.general.repository;

import com.pokemonurpg.general.models.Obtained;
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
