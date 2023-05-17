package com.pokemonurpg.repository;

import com.pokemonurpg.object.Evolution;
import com.pokemonurpg.object.EvolutionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvolutionRepository extends JpaRepository<Evolution, EvolutionKey> {
    Evolution findByIdEvolutionDbid(int evolutionDbid);
    List<Evolution> findByIdPreEvolutionDbid(int preEvolutionDbid);
}
