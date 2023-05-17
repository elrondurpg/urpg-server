package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.ParkLocation;
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
