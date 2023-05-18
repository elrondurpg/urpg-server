package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.EliteFourMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EliteFourMemberRepository extends JpaRepository<EliteFourMember, String> {
    @Query("select s.name from KnownEliteFourMember s")
    List<String> findAllNames();
    EliteFourMember findByDbid(int dbid);
    EliteFourMember findByName(String name);
    EliteFourMember findFirstByNameStartingWith(String name);
}
