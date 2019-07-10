package com.pokemonurpg.repository;

import com.pokemonurpg.object.ParkRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkRankRepository extends JpaRepository<ParkRank, Integer> {
    Optional<ParkRank> findByDbid(int dbid);
    Optional<ParkRank> findByName(String name);
    List<ParkRank> findByNameStartingWith(String name);
}
