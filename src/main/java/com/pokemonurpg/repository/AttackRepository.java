package com.pokemonurpg.repository;

import com.pokemonurpg.object.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AttackRepository extends JpaRepository<Attack, Integer> {
    @Query("select a.name from Attack a")
    List<Object> findAllNames();
    Attack findByName(String name);
    Attack findByDbid(Integer dbid);
    List<Attack> findByNameStartingWith(String name);
}
