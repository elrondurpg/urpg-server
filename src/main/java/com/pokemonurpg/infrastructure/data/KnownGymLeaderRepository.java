package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.KnownGymLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownGymLeaderRepository extends JpaRepository<KnownGymLeader, String> {
    @Query("select s.name from KnownGymLeader s")
    List<String> findAllNames();
    KnownGymLeader findByDbid(int dbid);
    KnownGymLeader findByName(String name);
    KnownGymLeader findFirstByNameStartingWith(String name);
}
