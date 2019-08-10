package com.pokemonurpg.repository;

import com.pokemonurpg.object.ParkRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkRankRepository extends JpaRepository<ParkRank, Integer> {
    ParkRank findByDbid(int dbid);
    ParkRank findByName(String name);
    List<ParkRank> findByNameStartingWith(String name);
}
