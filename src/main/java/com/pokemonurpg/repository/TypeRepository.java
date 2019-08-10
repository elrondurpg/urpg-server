package com.pokemonurpg.repository;

import com.pokemonurpg.object.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findByDbid(int dbid);
    Type findByName(String name);
    List<Type> findByNameStartingWith(String name);
}
