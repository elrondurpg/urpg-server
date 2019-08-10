package com.pokemonurpg.repository;

import com.pokemonurpg.object.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
    Ability findByName(String name);
    Ability findByDbid(Integer dbid);
    List<Ability> findByNameStartingWith(String name);
}
