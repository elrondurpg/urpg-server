package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.SendType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SendTypeRepository extends JpaRepository<SendType, Integer> {
    @Query("select t.name from SendType t")
    List<Object> findAllNames();
    SendType findByDbid(int dbid);
    SendType findByName(String name);
    List<SendType> findByNameStartingWith(String name);
}
