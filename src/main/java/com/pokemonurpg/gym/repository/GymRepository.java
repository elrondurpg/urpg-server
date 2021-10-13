package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymOwnershipTerm;
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
