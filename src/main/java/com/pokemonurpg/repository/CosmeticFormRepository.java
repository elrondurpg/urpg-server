package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.CosmeticForm;
import com.pokemonurpg.object.pokemon.CosmeticFormKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CosmeticFormRepository extends JpaRepository<CosmeticForm, CosmeticFormKey> {
    List<CosmeticForm> findByIdSpeciesDbid(int speciesDbid);
    CosmeticForm findByIdSpeciesDbidAndIdName(int speciesDbid, String formName);
}
