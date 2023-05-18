package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {
    @Query("select t.name from Badge t")
    List<String> findAllNames();
    Badge findByDbid(int dbid);
    Badge findByName(String name);
    Badge findFirstByNameStartingWith(String name);
    void deleteByDbid(int dbid);
}
