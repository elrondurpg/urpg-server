package com.pokemonurpg.repository;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.AttackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttackCategoryRepository extends JpaRepository<AttackCategory, Integer> {
    @Query("select x.name from AttackCategory x")
    List<Object> findAllNames();
    AttackCategory findByDbid(int dbid);
    AttackCategory findByName(String name);
    List<AttackCategory> findByNameStartingWith(String name);
}
