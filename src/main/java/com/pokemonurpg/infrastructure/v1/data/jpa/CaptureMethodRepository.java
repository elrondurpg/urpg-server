package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.CaptureMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaptureMethodRepository extends JpaRepository<CaptureMethod, Integer> {
    @Query("select t.name from Obtained t")
    List<String> findAllNames();
    CaptureMethod findByDbid(int dbid);
    CaptureMethod findByName(String name);
    CaptureMethod findFirstByNameStartingWith(String name);
}
