package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select x.username from Member x")
    List<Object> findAllNames();
    Member findByDbid(int dbid);
    Member findByUsername(String name);
    Member findByDiscordId(String discordId);
    List<Member> findByUsernameStartingWith(String username);
}
