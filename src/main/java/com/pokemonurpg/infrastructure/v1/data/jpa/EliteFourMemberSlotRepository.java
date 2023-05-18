package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EliteFourMemberSlotRepository extends JpaRepository<EliteFourMemberSlot, Integer> {
    @Query("select s.name from EliteFour s")
    List<String> findAllNames();
    EliteFourMemberSlot findByDbid(Integer dbid);
    EliteFourMemberSlot findByName(String name);
    EliteFourMemberSlot findFirstByNameStartingWith(String name);
    EliteFourMemberSlot findByCurrentOwnerRecord(EliteFourMemberRecord currentOwnerRecord);
    void deleteByDbid(int dbid);
}
