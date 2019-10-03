package com.pokemonurpg.repository;

import com.pokemonurpg.object.AttackCategory;
import com.pokemonurpg.object.AttackTargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttackTargetTypeRepository extends JpaRepository<AttackTargetType, Integer> {
    @Query("select x.name from AttackTargetType x")
    List<Object> findAllNames();
    AttackTargetType findByDbid(int dbid);
    AttackTargetType findByName(String name);
    List<AttackTargetType> findByNameStartingWith(String name);
}
