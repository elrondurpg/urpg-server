package com.pokemonurpg.repository;

import com.pokemonurpg.object.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    Optional<Type> findByDbid(int dbid);
    Optional<Type> findByName(String name);
    List<Type> findByNameStartingWith(String name);
}
