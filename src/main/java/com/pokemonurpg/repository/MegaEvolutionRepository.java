package com.pokemonurpg.repository;

import com.pokemonurpg.object.MegaEvolution;
import com.pokemonurpg.object.MegaEvolutionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MegaEvolutionRepository extends JpaRepository<MegaEvolution, MegaEvolutionKey> {
    List<MegaEvolution> findByIdOriginalDbid(int originalDbid);
}