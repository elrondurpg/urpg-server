package com.pokemonurpg.repository;

import com.pokemonurpg.object.ArtRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtRankRepository extends JpaRepository<ArtRank, Integer> {
    Optional<ArtRank> findByDbid(int dbid);
    Optional<ArtRank> findByName(String name);
    List<ArtRank> findByNameStartingWith(String name);
}
