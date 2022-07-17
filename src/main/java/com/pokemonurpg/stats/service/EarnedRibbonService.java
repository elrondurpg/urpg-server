package com.pokemonurpg.stats.service;

import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.contest.repository.ContestAttributeRepository;
import com.pokemonurpg.contest.repository.ContestRankRepository;
import com.pokemonurpg.contest.repository.ContestTypeRepository;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.EarnedRibbonRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

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
