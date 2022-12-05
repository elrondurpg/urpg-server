package com.pokemonurpg.entities.v1.stats;

import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.entities.v1.contest.ContestType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedRibbonRepository extends JpaRepository<EarnedRibbon, Integer> {
    List<EarnedRibbon> findByPokemon(OwnedPokemon pokemon);
    EarnedRibbon findByPokemonAndIdLogUrlAndGenerationAndRankAndAttribute(OwnedPokemon pokemon, String logUrl, ContestType generation, ContestRank rank, ContestAttribute attribute);
}