package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.rank.model.ContestRank;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedRibbonRepository extends JpaRepository<EarnedRibbon, Integer> {
    List<EarnedRibbon> findByPokemon(OwnedPokemon pokemon);
    EarnedRibbon findByPokemonAndIdLogUrlAndGenerationAndRankAndAttribute(OwnedPokemon pokemon, String logUrl, ContestType generation, ContestRank rank, ContestAttribute attribute);
}