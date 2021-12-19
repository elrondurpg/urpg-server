package com.pokemonurpg.general.repository;

import com.pokemonurpg.general.models.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlagRepository extends JpaRepository<Flag, Integer> {
    Flag findByDbid(int dbid);
    Flag findByName(String name);
    Flag findFirstByNameStartingWith(String name);
}
