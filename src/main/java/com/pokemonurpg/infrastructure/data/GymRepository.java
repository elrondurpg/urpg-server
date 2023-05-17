package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Integer> {
    @Query("select s.name from Gym s")
    List<String> findAllNames();
    Gym findByDbid(Integer dbid);
    Gym findByName(String name);
    Gym findFirstByNameStartingWith(String name);
    Gym findByCurrentOwnerRecord(GymOwnershipTerm currentOwnerRecord);
    void deleteByDbid(int dbid);
}
