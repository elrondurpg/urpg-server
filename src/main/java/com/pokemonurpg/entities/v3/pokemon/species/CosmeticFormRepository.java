package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticFormRepository  extends JpaRepository<CosmeticFormEntity, String> {
    Set<CosmeticFormEntity> findBySpeciesDbid(Integer speciesDbid);
    CosmeticFormEntity findByName(String name);
    CosmeticFormEntity findFirstByNameStartingWith(String name);
}
