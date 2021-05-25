package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.KnownGymLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownGymLeaderRepository extends JpaRepository<KnownGymLeader, String> {
    @Query("select s.name from KnownGymLeader s")
    List<String> findAllNames();
    KnownGymLeader findByName(String name);
    KnownGymLeader findFirstByNameStartingWith(String name);
}
