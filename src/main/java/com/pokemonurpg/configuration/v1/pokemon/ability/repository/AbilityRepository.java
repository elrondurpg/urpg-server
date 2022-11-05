package com.pokemonurpg.configuration.v1.pokemon.ability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
    @Query("select a.name from Ability a")
    List<String> findAllNames();
    Ability findByName(String name);
    Ability findByDbid(Integer dbid);
    Ability findFirstByNameStartingWith(String name);
    List<Ability> findByNameStartingWith(String name);
}
