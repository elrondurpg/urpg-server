package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttackRepository extends JpaRepository<Attack, Integer> {
    @Query("select a.name from Attack a")
    List<String> findAllNames();
    Attack findByName(String name);
    Attack findByDbid(Integer dbid);
    Attack findFirstByNameStartingWith(String name);
    List<Attack> findByNameStartingWith(String name);
}
