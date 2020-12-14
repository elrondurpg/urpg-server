package com.pokemonurpg.member.repository;

import com.pokemonurpg.member.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select x.username from Member x")
    List<String> findAllNames();
    Member findByDbid(int dbid);
    Member findByUsername(String name);
    Member findByDiscordId(String discordId);
    Member findFirstByUsernameStartingWith(String username);
}
