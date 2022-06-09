package com.pokemonurpg.species.repository;

import com.pokemonurpg.species.models.CosmeticForm;
import com.pokemonurpg.species.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticFormRepository  extends JpaRepository<CosmeticForm, String> {
    CosmeticForm findByName(String name);
    CosmeticForm findFirstByNameStartingWith(String name);
}
