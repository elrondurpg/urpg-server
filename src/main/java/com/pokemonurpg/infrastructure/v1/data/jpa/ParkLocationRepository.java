package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ParkLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkLocationRepository extends JpaRepository<ParkLocation, Integer> {
    @Query("select a.name from ParkLocation a")
    List<String> findAllNames();
    ParkLocation findByName(String name);
    ParkLocation findByDbid(Integer dbid);
    ParkLocation findFirstByNameStartingWith(String name);
}
