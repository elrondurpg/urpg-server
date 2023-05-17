package com.pokemonurpg.stats.repository;

import com.pokemonurpg.entities.ContestAttribute;
import com.pokemonurpg.entities.ContestRank;
import com.pokemonurpg.entities.ContestType;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedRibbonRepository extends JpaRepository<EarnedRibbon, Integer> {
    List<EarnedRibbon> findByPokemon(OwnedPokemon pokemon);
    EarnedRibbon findByPokemonAndIdLogUrlAndGenerationAndRankAndAttribute(OwnedPokemon pokemon, String logUrl, ContestType generation, ContestRank rank, ContestAttribute attribute);
}