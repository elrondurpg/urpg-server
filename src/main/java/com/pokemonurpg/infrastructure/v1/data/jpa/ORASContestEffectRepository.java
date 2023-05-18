package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ORASContestEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ORASContestEffectRepository extends JpaRepository<ORASContestEffect, Integer> {
    @Query("select a.name from ORASContestMoveType a")
    List<String> findAllNames();
    ORASContestEffect findByName(String name);
    ORASContestEffect findByDbid(Integer dbid);
    ORASContestEffect findFirstByNameStartingWith(String name);
}
