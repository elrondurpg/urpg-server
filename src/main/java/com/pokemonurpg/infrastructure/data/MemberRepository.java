package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select x.name from Member x")
    List<String> findAllNames();
    Member findByDbid(Integer dbid);
    Member findByName(String name);
    Member findByDiscordId(String discordId);
    Member findFirstByNameStartingWith(String name);
    void deleteByDbid(int dbid);
}
