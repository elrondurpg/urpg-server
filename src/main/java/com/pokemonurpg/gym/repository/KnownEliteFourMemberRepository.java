package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.KnownEliteFourMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownEliteFourMemberRepository extends JpaRepository<KnownEliteFourMember, String> {
    @Query("select s.name from KnownEliteFourMember s")
    List<String> findAllNames();
    KnownEliteFourMember findByName(String name);
    KnownEliteFourMember findFirstByNameStartingWith(String name);
}
