package com.pokemonurpg.repository;

import com.pokemonurpg.object.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    @Query("select t.name from Type t")
    List<Object> findAllNames();
    Type findByDbid(int dbid);
    Type findByName(String name);
    List<Type> findByNameStartingWith(String name);
}
