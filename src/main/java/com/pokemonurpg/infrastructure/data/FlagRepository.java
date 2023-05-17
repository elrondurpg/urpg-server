package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlagRepository extends JpaRepository<Flag, Integer> {
	@Query("select t.name from Flag t")
    List<String> findAllNames();
    Flag findByDbid(int dbid);
    Flag findByName(String name);
    Flag findFirstByNameStartingWith(String name);
}
