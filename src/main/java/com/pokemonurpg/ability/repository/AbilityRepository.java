package com.pokemonurpg.ability.repository;

import com.pokemonurpg.ability.models.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
    @Query("select a.name from Ability a")
    List<String> findAllNames();
    Ability findByName(String name);
    Ability findByDbid(Integer dbid);
    Ability findFirstByNameStartingWith(String name);
    List<Ability> findByNameStartingWith(String name);
}
