package com.pokemonurpg.attack.repository;

import com.pokemonurpg.attack.models.AttackTargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttackTargetTypeRepository extends JpaRepository<AttackTargetType, Integer> {
    @Query("select x.name from AttackTargetType x")
    List<String> findAllNames();
    AttackTargetType findByDbid(int dbid);
    AttackTargetType findByName(String name);
    AttackTargetType findFirstByNameStartingWith(String name);
}
