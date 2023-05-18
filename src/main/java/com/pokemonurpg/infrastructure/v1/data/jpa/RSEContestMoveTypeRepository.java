package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.RSEContestEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RSEContestMoveTypeRepository extends JpaRepository<RSEContestEffect, Integer> {
    @Query("select a.name from RSEContestMoveType a")
    List<String> findAllNames();
    RSEContestEffect findByName(String name);
    RSEContestEffect findByDbid(Integer dbid);
    RSEContestEffect findFirstByNameStartingWith(String name);
}
