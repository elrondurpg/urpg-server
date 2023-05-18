package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.GymLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymLeaderRepository extends JpaRepository<GymLeader, String> {
    @Query("select s.name from KnownGymLeader s")
    List<String> findAllNames();
    GymLeader findByDbid(int dbid);
    GymLeader findByName(String name);
    GymLeader findFirstByNameStartingWith(String name);
}
