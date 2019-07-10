package com.pokemonurpg.repository;

import com.pokemonurpg.object.ParkLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkLocationRepository extends JpaRepository<ParkLocation, Integer> {
    Optional<ParkLocation> findByDbid(int dbid);
    Optional<ParkLocation> findByName(String name);
    List<ParkLocation> findByNameStartingWith(String name);
}
