package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
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
