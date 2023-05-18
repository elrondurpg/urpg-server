package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.AttackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttackCategoryRepository extends JpaRepository<AttackCategory, Integer> {
    @Query("select x.name from AttackCategory x")
    List<String> findAllNames();
    AttackCategory findByDbid(int dbid);
    AttackCategory findByName(String name);
    AttackCategory findFirstByNameStartingWith(String name);
}
