package com.pokemonurpg.configuration.v1.pokemon.species.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;

public interface CosmeticFormRepository  extends JpaRepository<CosmeticForm, String> {
    Set<CosmeticForm> findBySpeciesDbid(Integer speciesDbid);
    CosmeticForm findByName(String name);
    CosmeticForm findFirstByNameStartingWith(String name);
}
