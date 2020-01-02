package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.RSEContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RSEContestMoveTypeRepository extends JpaRepository<RSEContestMoveType, Integer> {
    @Query("select a.name from RSEContestMoveType a")
    List<Object> findAllNames();
    RSEContestMoveType findByName(String name);
    RSEContestMoveType findByDbid(Integer dbid);
    List<RSEContestMoveType> findByNameStartingWith(String name);
}
