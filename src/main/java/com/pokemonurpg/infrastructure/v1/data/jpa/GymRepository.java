package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Integer> {
    @Query("select s.name from Gym s")
    List<String> findAllNames();
    Gym findByDbid(Integer dbid);
    Gym findByName(String name);
    Gym findFirstByNameStartingWith(String name);
    Gym findByCurrentOwnerRecord(GymLeaderRecord currentOwnerRecord);
    void deleteByDbid(int dbid);
}
