package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymOwnershipTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EliteFourRepository extends JpaRepository<EliteFour, Integer> {
    @Query("select s.name from EliteFour s")
    List<String> findAllNames();
    EliteFour findByDbid(Integer dbid);
    EliteFour findByName(String name);
    EliteFour findFirstByNameStartingWith(String name);
    EliteFour findByCurrentOwnerRecord(EliteFourOwnershipTerm currentOwnerRecord);
    void deleteByDbid(int dbid);
}