package com.pokemonurpg.repository;

import com.pokemonurpg.object.ParkLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkLocationRepository extends JpaRepository<ParkLocation, Integer> {
    @Query("select x.name from ParkLocation x")
    List<Object> findAllNames();
    ParkLocation findByDbid(int dbid);
    ParkLocation findByName(String name);
    List<ParkLocation> findByNameStartingWith(String name);
}
