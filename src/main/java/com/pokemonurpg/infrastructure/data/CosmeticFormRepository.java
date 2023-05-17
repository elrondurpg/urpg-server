package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.CosmeticForm;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticFormRepository  extends JpaRepository<CosmeticForm, String> {
    Set<CosmeticForm> findBySpeciesDbid(Integer speciesDbid);
    CosmeticForm findByName(String name);
    CosmeticForm findFirstByNameStartingWith(String name);
}
