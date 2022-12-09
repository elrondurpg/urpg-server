package com.pokemonurpg.stats.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.ContestAttributeRepository;
import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.entities.v1.contest.ContestRankRepository;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.entities.v1.contest.ContestTypeRepository;
import com.pokemonurpg.entities.v1.stats.EarnedRibbon;
import com.pokemonurpg.entities.v1.stats.EarnedRibbonRepository;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;

@Service
public class EarnedRibbonService {

    @Resource
    private EarnedRibbonRepository earnedRibbonRepository;

    @Resource
    private ContestRankRepository contestRankRepository;

    @Resource
    private ContestAttributeRepository contestAttributeRepository;

    @Resource
    private ContestTypeRepository contestTypeRepository;

    List<EarnedRibbon> findByOwnedPokemon(OwnedPokemon pokemon) {
        return earnedRibbonRepository.findByPokemon(pokemon);
    }

    public void update(EarnedRibbonInputDto input, OwnedPokemon pokemon) {
        ContestRank rank = contestRankRepository.findByName(input.getRank());
        ContestAttribute attribute = contestAttributeRepository.findByName(input.getAttribute());
        ContestType generation = contestTypeRepository.findByName(input.getGeneration());
        EarnedRibbon existingRecord = earnedRibbonRepository.findByPokemonAndIdLogUrlAndGenerationAndRankAndAttribute(pokemon, input.getLogUrl(), generation, rank, attribute);
        if (existingRecord != null) {
            if (input.getDelete()) {
                earnedRibbonRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                earnedRibbonRepository.save(existingRecord);
            }
        }
        else {
            EarnedRibbon earnedRibbon = new EarnedRibbon(input, pokemon, generation, rank, attribute);
            earnedRibbonRepository.save(earnedRibbon);
        }
    }
}
