package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.entities.v1.ContestType;
import com.pokemonurpg.entities.v1.EarnedRibbon;
import com.pokemonurpg.entities.v1.OwnedPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedRibbonRepository extends JpaRepository<EarnedRibbon, Integer> {
    List<EarnedRibbon> findByPokemon(OwnedPokemon pokemon);
    EarnedRibbon findByPokemonAndIdLogUrlAndGenerationAndRankAndAttribute(OwnedPokemon pokemon, String logUrl, ContestType generation, ContestRank rank, ContestAttribute attribute);
}