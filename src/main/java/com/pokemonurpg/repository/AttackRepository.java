package com.pokemonurpg.repository;

import com.pokemonurpg.object.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttackRepository extends JpaRepository<Attack, Integer> {
    Optional<Attack> findByName(String name);
    Optional<Attack> findByDbid(Integer dbid);
    List<Attack> findByNameStartingWith(String name);
}
