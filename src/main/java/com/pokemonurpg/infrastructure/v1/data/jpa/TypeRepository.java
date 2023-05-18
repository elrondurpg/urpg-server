package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findAll();
    @Query("select t.name from Type t")
    List<String> findAllNames();
    Type findByDbid(int dbid);
    Type findByName(String name);
    Type findFirstByNameStartingWith(String name);
}
