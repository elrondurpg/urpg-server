package com.pokemonurpg.repository;

import com.pokemonurpg.object.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
    @Query("select a.name from Ability a")
    List<Object> findAllNames();
    Ability findByName(String name);
    Ability findByDbid(Integer dbid);
    List<Ability> findByNameStartingWith(String name);
}
