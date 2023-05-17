package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.RSEContestMoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RSEContestMoveTypeRepository extends JpaRepository<RSEContestMoveType, Integer> {
    @Query("select a.name from RSEContestMoveType a")
    List<String> findAllNames();
    RSEContestMoveType findByName(String name);
    RSEContestMoveType findByDbid(Integer dbid);
    RSEContestMoveType findFirstByNameStartingWith(String name);
}
